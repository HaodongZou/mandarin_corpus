package cn.zouhd.mandarinCorpus.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "corpus_subcategory")
public class Subcategory {

    @Id
    private Integer id;

    @Column
    private String category;
    @Column
    private String name;
    @Column
    private String abbr;

}
