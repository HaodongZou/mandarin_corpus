package cn.zouhd.mandarinCorpus.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HanwaiExcelDataListenerTest {

    @Autowired
    MockMvc mockMvc;



    @Test
    void invoke() {
    }

    @Test
    void doAfterAllAnalysed() {
        // 小狼毫输入法还挺好用的
        // 这是微软拼音输入法其实也挺好用的
        // nishuozhendema jkdjf skjdksfjelskjf 开设开发技术开始skjeijfnskjf上课JFK的机械能开始提供 skdjkejfkjs看似简单科技覅
        // for ingt int 你是做得快这是真的吗这可真的帮了大忙了这样的配色还好看吗
    }
}