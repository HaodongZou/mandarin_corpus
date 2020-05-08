package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Yunshu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
