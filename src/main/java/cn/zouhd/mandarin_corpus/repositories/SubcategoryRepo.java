package cn.zouhd.mandarin_corpus.repositories;

import cn.zouhd.mandarin_corpus.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Integer> {

    List<Subcategory> findByNameLike (String name);

    List<Subcategory> findByCategoryLike (String category);

    List<Subcategory> findByCategoryLikeAndNameLike (String category, String name);
}
