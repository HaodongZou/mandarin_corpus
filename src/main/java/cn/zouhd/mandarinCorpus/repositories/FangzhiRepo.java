package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Fangzhi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FangzhiRepo extends JpaRepository<Fangzhi, Integer> {

    List<Fangzhi> findByNameLike(String name);
}
