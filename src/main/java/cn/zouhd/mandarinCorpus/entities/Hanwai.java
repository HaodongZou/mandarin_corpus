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
    Integer id;

    /**
     * 单字.
     */
    String word;

    /**
     *  自迩集 音.
     */
    String zejYin;
    /**
     *  自迩集 韵.
     */
    String zejYun;
    /**
     *  自迩集 声.
     */
    String zejSheng;
    /**
     *  尋津彔 音.
     */
    String xjlYin;
    /**
     *  尋津彔 韵.
     */
    String xjlYun;
    /**
     *  尋津彔 声.
     */
    String xjlSheng;
    /**
     *  华英音(华英文义津逮) 音.
     */
    String hywyjdYin;
    /**
     *  华英音(华英文义津逮) 韵.
     */
    String hywyjdYun;
    /**
     *  华英音(华英文义津逮) 声.
     */
    String hywyjdSheng;
    /**
     *  唐話類纂 拟音.
     */
    String thzyNiyin;
    /**
     *  唐話類纂 标音.
     */
    String thzyBiaoyin;
    /**
     *  四聲通解 备注.
     */
    String sstjBeizhu;
    /**
     *  四聲通解 声调.
     */
    String sstjShengdiao;
    /**
     *  四聲通解 今俗音.
     */
    String sstjJinsuyin;
    /**
     *  華英啟蒙諺解 韵.
     */
    String hyqmyjYun;
    /**
     *  華英啟蒙諺解 声.
     */
    String hyqmyjSheng;
}
