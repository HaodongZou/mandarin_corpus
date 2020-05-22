package cn.zouhd.mandarinCorpus.listener;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import cn.zouhd.mandarinCorpus.repositories.HanwaiRepo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HanwaiExcelDataListener extends AnalysisEventListener<Hanwai> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HanwaiExcelDataListener.class);

    /**
     * 每隔 BATCH_COUNT 条存储数据库
     */
    private static final int BATCH_COUNT = 1000;

    List<Hanwai> list = new ArrayList<>();

    private HanwaiRepo hanwaiRepo;

    public HanwaiExcelDataListener(HanwaiRepo hanwaiRepo){
        this.hanwaiRepo = hanwaiRepo;
    }

    @Override
    public void invoke(Hanwai hanwai, AnalysisContext analysisContext) {
        if (!"{}".equals(JSON.toJSONString(hanwai))){
            list.add(hanwai);
            if (list.size() >= BATCH_COUNT) {
                saveData();
                // 存储完成清理 list
                list.clear();
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        LOGGER.info("所有数据已经保存完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        hanwaiRepo.saveAll(list);
        LOGGER.info("存储数据库成功！");
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        LOGGER.error("第{}行解析失败，继续解析下一行：{}", context.getCurrentRowNum(), exception.getMessage());
    }
}
