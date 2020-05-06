package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Hanwai repo.
 *
 * @author zouhd
 * @date 2020 -05-06 21:59:15
 */
public interface HanwaiRepo extends JpaRepository<Hanwai, Integer> {

    /**
     * Find by zej sheng not null or zej yin not null or zej yun not null list.
     *
     * @param pageable the pageable
     * @return the list
     */
    List<Hanwai> findByZejShengNotNullOrZejYinNotNullOrZejYunNotNull (Pageable pageable);

    List<Hanwai> findByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull (Pageable pageable);

    List<Hanwai> findByHyqmyjShengNotNullOrHyqmyjYunNotNull (Pageable pageable);

    List<Hanwai> findByHywyjdShengNotNullOrHywyjdYinNotNullOrHywyjdYunNotNull (Pageable pageable);

    List<Hanwai> findBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjZhuyinNotNull (Pageable pageable);

    List<Hanwai> findByThzyBiaoyinNotNullOrThzyNiyinNotNull (Pageable pageable);
}
