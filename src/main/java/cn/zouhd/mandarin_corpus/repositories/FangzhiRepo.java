package cn.zouhd.mandarin_corpus.repositories;

import cn.zouhd.mandarin_corpus.entities.Fangzhi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FangzhiRepo extends JpaRepository<Fangzhi, Integer> {

    List<Fangzhi> findByNameLike(String name);
}
