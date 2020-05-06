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
    Integer id;

    /**
     *  Word.
     */
    String word;
    /**
     *  摄等开合调.
     */
    String sdkhd;
    /**
     *  韵.
     */
    String yun;
    /**
     *  声.
     */
    String sheng;
    /**
     *  中原 音.
     */
    String zyYin;
    /**
     *  中原 韵.
     */
    String zyYun;
    /**
     *  中原 声.
     */
    String zySheng;
}
