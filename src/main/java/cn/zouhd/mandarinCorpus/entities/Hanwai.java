package cn.zouhd.mandarinCorpus.entities;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty("编号")
    private Integer id;

    /**
     * 单字.
     */
    @ExcelProperty("字")
    private String word;

    /**
     *  自迩集 音.
     */
    @ExcelProperty("语言自迩集音")
    private String zejYin;
    /**
     *  自迩集 韵.
     */
    @ExcelProperty("自迩集韵")
    private String zejYun;
    /**
     *  自迩集 声.
     */
    @ExcelProperty("自迩集声")
    private String zejSheng;
    /**
     *  尋津彔 音.
     */
    @ExcelProperty("尋津彔音")
    private String xjlYin;
    /**
     *  尋津彔 韵.
     */
    @ExcelProperty("尋津彔韻")
    private String xjlYun;
    /**
     *  尋津彔 声.
     */
    @ExcelProperty("尋津彔聲")
    private String xjlSheng;
    /**
     *  华英音(华英文义津逮) 音.
     */
    @ExcelProperty("华英音")
    private String hywyjdYin;
    /**
     *  华英音(华英文义津逮) 韵.
     */
    @ExcelProperty("华英韵")
    private String hywyjdYun;
    /**
     *  华英音(华英文义津逮) 声.
     */
    @ExcelProperty("华英声")
    private String hywyjdSheng;
    /**
     *  唐話類纂 拟音.
     */
    @ExcelProperty("唐話類纂擬音")
    private String thzyNiyin;
    /**
     *  唐話類纂 标音.
     */
    @ExcelProperty("唐話類纂標音")
    private String thzyBiaoyin;
    /**
     *  四聲通解 备注.
     */
    @ExcelProperty("四聲通解备注")
    private String sstjBeizhu;
    /**
     *  四聲通解 声调.
     */
    @ExcelProperty("四聲通解声调")
    private String sstjShengdiao;
    /**
     *  四聲通解 今俗音.
     */
    @ExcelProperty("四聲通解今俗音")
    private String sstjJinsuyin;
    /**
     *  華英啟蒙諺解 韵.
     */
    @ExcelProperty("華英啟蒙諺解韻")
    private String hyqmyjYun;
    /**
     *  華英啟蒙諺解 声.
     */
    @ExcelProperty("華英啟蒙諺解聲")
    private String hyqmyjSheng;
}
