package cn.zouhd.mandarin_corpus.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "corpus_yunshu")
@Data
public class Yunshu {

    @Id
    private Integer id;
    @Column
    private String character;
    @Column
    private String y1602;
    @Column
    private String y1611;
    @Column
    private String y1661t1670;
    @Column
    private String y1711;
    @Column
    private String y1674;
    @Column
    private String y1699t1704;
    @Column
    private String y1726;
    @Column
    private String qianLongWanNian;
    @Column
    private String y1805;
    @Column
    private String y1817;
    @Column
    private String y1805t1858;
    @Column
    private String y1840;
    @Column
    private String y1863;
    @Column
    private String y1912;
}
