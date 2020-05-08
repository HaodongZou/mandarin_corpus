package cn.zouhd.mandarinCorpus.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  type Template.
 *
 * @author zouhd
 * @date 2020 -05-06 20:40:54
 */
@Entity
@Data
@Table(name = "corpus_template")
public class Template {

    /**
     *  Id.
     */
    @Id
    private Integer id;

    /**
     *  Word.
     */
    private String word;
    /**
     *  摄等开合调.
     */
    private String sdkhd;
    /**
     *  韵.
     */
    private String yun;
    /**
     *  声.
     */
    private String sheng;
    /**
     *  中原 音.
     */
    private String zyYin;
    /**
     *  中原 韵.
     */
    private String zyYun;
    /**
     *  中原 声.
     */
    private String zySheng;
}
