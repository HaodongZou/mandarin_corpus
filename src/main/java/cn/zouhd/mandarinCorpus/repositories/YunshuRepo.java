package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Yunshu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Yunshu repo.
 *
 * @author zouhd
 * @date 2020 -04-18 18:35:11
 */
public interface YunshuRepo extends JpaRepository<Yunshu, Integer> {

    /**
     * 模糊查询字符
     *
     * @param character the character
     * @return the list
     */
    List<Yunshu> findByWordLike(String character);

}
