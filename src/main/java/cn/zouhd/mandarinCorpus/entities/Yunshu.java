package cn.zouhd.mandarinCorpus.entities;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 韵书韵图模块.
 *
 * @author zouhd
 * @date 2020 -05-08 11:45:46
 */
@Entity
@Table(name = "corpus_yunshu")
@Data
public class Yunshu {

    /**
     *  Id.
     */
    @Id
    @ExcelProperty("编号")
    private Integer id;

    /**
     *  字.
     */
    @ExcelProperty("字")
    private String word;
    /**
     * 京音字汇 声.
     */
    @ExcelProperty("京音字彙聲")
    private String jyzhSheng;
    /**
     * 京音字汇 调.
     */
    @ExcelProperty("京音字彙調")
    private String jyzhDiao;
    /**
     * 京音字汇 韵.
     */
    @ExcelProperty("京音字彙韻")
    private String jyzhYun;
    /**
     *  国音常用字汇 声.
     */
    @ExcelProperty("字彙声")
    private String gycyzhSheng;
    /**
     *  国音常用字汇 音.
     */
    @ExcelProperty("字彙音")
    private String gycyzhYin;
    /**
     *  国音常用字汇 韵.
     */
    @ExcelProperty("字彙韵")
    private String gycyzhYun;

}
