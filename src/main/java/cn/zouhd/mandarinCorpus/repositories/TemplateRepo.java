package cn.zouhd.mandarinCorpus.repositories;

import cn.zouhd.mandarinCorpus.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepo extends JpaRepository<Template, Integer> {

    List<Template> findByWord (String word);

}
