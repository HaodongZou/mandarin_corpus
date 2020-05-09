package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Yunshu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Yunshu repo.
 *
 * @author zouhd
 * @date 2020 -04-18 18:35:11
 */
public interface YunshuRepo extends JpaRepository<Yunshu, Integer> {

    List<Yunshu> findByGycyzhShengNotNullOrGycyzhYunNotNullOrGycyzhYinNotNull (Pageable pageable);

    List<Yunshu> findByJyzhDiaoNotNullOrJyzhShengNotNullOrJyzhYunNotNull (Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT cnt FROM ( SELECT id, count(gycyzh_sheng OR gycyzh_yin OR gycyzh_yun) over (ORDER BY id) cnt FROM corpus_yunshu) x WHERE id = ?1")
    Integer findGycyzhPage(Integer id);

    @Query(nativeQuery = true, value = "SELECT cnt FROM ( SELECT id, count(jyzh_diao OR jyzh_sheng OR jyzh_yun) over (ORDER BY id) cnt FROM corpus_yunshu) x WHERE id = ?1")
    Integer findJyzhPage(Integer id);

}
