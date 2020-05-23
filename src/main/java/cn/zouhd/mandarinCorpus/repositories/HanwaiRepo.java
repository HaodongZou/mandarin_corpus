package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Hanwai repo.
 *
 * @author zouhd
 * @date 2020 -05-06 21:59:15
 */
public interface HanwaiRepo extends JpaRepository<Hanwai, Integer> {

    List<Hanwai> findByWordLike(String word);

    List<Hanwai> findByZejShengNotNullOrZejYinNotNullOrZejYunNotNull (Pageable pageable);

    List<Hanwai> findByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull (Pageable pageable);

    List<Hanwai> findByHyqmyjShengNotNullOrHyqmyjYunNotNull (Pageable pageable);

    List<Hanwai> findByHywyjdShengNotNullOrHywyjdYinNotNullOrHywyjdYunNotNull (Pageable pageable);

    List<Hanwai> findBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjBeizhuNotNull (Pageable pageable);

    List<Hanwai> findByThzyBiaoyinNotNullOrThzyNiyinNotNull (Pageable pageable);

    @Query(nativeQuery=true, value="SELECT cnt FROM ( SELECT id, count(zej_sheng OR zej_yin OR zej_yun) over (ORDER BY id) cnt FROM corpus_hanwai) x WHERE id = ?1")
    Integer findZejPage(Integer id);

    @Query(nativeQuery=true, value="SELECT cnt FROM ( SELECT id, count(xjl_sheng OR xjl_yin OR xjl_yun) over (ORDER BY id) cnt FROM corpus_hanwai) x WHERE id = ?1")
    Integer findXjlPage(Integer id);

    @Query(nativeQuery=true, value="SELECT cnt FROM ( SELECT id, count(hywyjd_sheng OR hywyjd_yin OR hywyjd_yun) over (ORDER BY id) cnt FROM corpus_hanwai) x WHERE id = ?1")
    Integer findHywyjdPage(Integer id);

    @Query(nativeQuery=true, value="SELECT cnt FROM ( SELECT id, count(thzy_biaoyin OR thzy_niyin) over (ORDER BY id) cnt FROM corpus_hanwai) x WHERE id = ?1")
    Integer findThzyPage(Integer id);

    @Query(nativeQuery=true, value="SELECT cnt FROM ( SELECT id, count(sstj_beizhu OR sstj_jinsuyin OR sstj_shengdiao) over (ORDER BY id) cnt FROM corpus_hanwai) x WHERE id = ?1")
    Integer findSstjPage(Integer id);

    @Query(nativeQuery=true, value="SELECT cnt FROM ( SELECT id, count(hyqmyj_sheng OR hyqmyj_yun) over (ORDER BY id) cnt FROM corpus_hanwai) x WHERE id = ?1")
    Integer findHyqmyjPage(Integer id);

    Integer countByZejShengNotNullOrZejYinNotNullOrZejYunNotNull();

    Integer countByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull();

    Integer countByHyqmyjShengNotNullOrHyqmyjYunNotNull();

    Integer countByHywyjdShengNotNullOrHywyjdYinNotNullOrHywyjdYunNotNull();

    Integer countBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjBeizhuNotNull();

    Integer countByThzyBiaoyinNotNullOrThzyNiyinNotNull();


}
