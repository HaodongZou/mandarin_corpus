package cn.zouhd.mandarinCorpus.entities;

import lombok.Data;

import javax.persistence.*;

/**
 *  type Hanwai.
 *
 * @author zouhd
 * @date 2020 -05-06 20:33:52
 */
@Entity
@Data
@Table(name = "corpus_hanwai")
public class Hanwai {



    /**
     *  Id.
     */
    @Id
    private Integer id;

    /**
     * 单字.
     */
    private String word;

    /**
     *  自迩集 音.
     */
    private String zejYin;
    /**
     *  自迩集 韵.
     */
    private String zejYun;
    /**
     *  自迩集 声.
     */
    private String zejSheng;
    /**
     *  尋津彔 音.
     */
    private String xjlYin;
    /**
     *  尋津彔 韵.
     */
    private String xjlYun;
    /**
     *  尋津彔 声.
     */
    private String xjlSheng;
    /**
     *  华英音(华英文义津逮) 音.
     */
    private String hywyjdYin;
    /**
     *  华英音(华英文义津逮) 韵.
     */
    private String hywyjdYun;
    /**
     *  华英音(华英文义津逮) 声.
     */
    private String hywyjdSheng;
    /**
     *  唐話類纂 拟音.
     */
    private String thzyNiyin;
    /**
     *  唐話類纂 标音.
     */
    private String thzyBiaoyin;
    /**
     *  四聲通解 备注.
     */
    private String sstjBeizhu;
    /**
     *  四聲通解 声调.
     */
    private String sstjShengdiao;
    /**
     *  四聲通解 今俗音.
     */
    private String sstjJinsuyin;
    /**
     *  華英啟蒙諺解 韵.
     */
    private String hyqmyjYun;
    /**
     *  華英啟蒙諺解 声.
     */
    private String hyqmyjSheng;
}
