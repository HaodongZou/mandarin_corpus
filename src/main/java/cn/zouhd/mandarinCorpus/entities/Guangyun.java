package cn.zouhd.mandarinCorpus.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Guangyun.
 *
 * @author zouhd
 * @date 2020 -04-23 09:39:56
 */
@Data
@Entity
@Table(name = "corpus_guangyun")
public class Guangyun {

    @Id
    private Integer id;
    private Integer shn;
    private Integer n;
    private String code;
    // 单字
    private String word;
    // 反切
    private String fanqie;
    // 声
    private String sheng;
    // 韵
    private String yun;
    // 口
    private String kou;
    // 等
    private String deng;
    // 调
    private String diao;
    // 摄
    private String she;
    // 声母拟音
    private String shengmuniyin;
    // 韵母拟音
    private String yunmuniyin;
    // 拟音
    private String niyin;
    // 注释
    private String zhushi;


}
