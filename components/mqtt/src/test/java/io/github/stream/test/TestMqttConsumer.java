package io.github.stream.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author wendy512@yeah.net
 * @date 2023-05-22 15:39:17
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class TestMqttConsumer {

    @Test
    public void testConsumer() throws Exception {
        // sleep 过程使用mqtt工具发送消息
        TimeUnit.SECONDS.sleep(60);
    }
}