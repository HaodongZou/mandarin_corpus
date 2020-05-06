package cn.zouhd.mandarinCorpus.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "corpus_yunshu")
@Data
public class Yunshu {

    @Id
    private Integer id;
    private String word;
    private String y1602;
    private String y1611;
    private String y1661t1670;
    private String y1711;
    private String y1674;
    private String y1699t1704;
    private String y1726;
    private String qianLongWanNian;
    private String y1805;
    private String y1817;
    private String y1805t1858;
    private String y1840;
    private String y1863;
    private String y1912;
    private String y1921;
//    private String y1581;
//    private String y1603;
//    private String y1632;
//    private String y1776;
//    private String y1642;
//    private String y1739;
//    private String y1849;
//    private String y1587;
//    private String y1921;
//    private String y1921;
//    private String y1921;
//    private String y1921;

}
