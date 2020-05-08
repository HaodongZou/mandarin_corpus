package cn.zouhd.mandarinCorpus.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "corpus_fangzhi")
public class Fangzhi {

    @Id
    private Integer id;
    //TODO: 有待修改方志数据库，添加各自对应的URI等。。
    @Column(name = "fangzhi")
    private String name;
}
