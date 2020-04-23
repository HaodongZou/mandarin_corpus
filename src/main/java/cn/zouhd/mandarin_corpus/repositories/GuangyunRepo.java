package cn.zouhd.mandarin_corpus.repositories;

import cn.zouhd.mandarin_corpus.entities.Guangyun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Guangyun repo.
 *
 * @author zouhd
 * @date 2020 -04-23 09:45:25
 */
public interface GuangyunRepo extends JpaRepository<Guangyun, Integer> {

    /**
     * 单字模糊查询
     *
     * @param word the word
     * @return the list
     */
    List<Guangyun> findByWordLike(String word);

    /**
     * 反切模糊查询
     *
     * @param fanqie the fanqie
     * @return the list
     */
    List<Guangyun> findByFanqieLike(String fanqie);

    /**
     * 声模糊查询
     *
     * @param sheng the sheng
     * @return the list
     */
    List<Guangyun> findByShengLike(String sheng);

    /**
     * 韵模糊查询
     *
     * @param yun the yun
     * @return the list
     */
    List<Guangyun> findByYunLike(String yun);
}
