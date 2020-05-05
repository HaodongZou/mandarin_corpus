package cn.zouhd.mandarin_corpus.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "corpus.subcategory")
public class Subcategory {

    @Id
    Integer id;

    @Column
    String category;
    @Column
    String name;

}
