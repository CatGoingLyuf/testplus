package com.example.demo1.kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author lyuf
 * @Date 2022/1/25 10:19
 * @Version 1.0
 */
@Controller
@Component
@RequestMapping("/api")
public class sentDemo {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @Value("${spring.kafka.topic}")
    private String topic;

    private static final Logger LOGGER = LoggerFactory.getLogger(sentDemo.class);

    @PostMapping
    public void sent(@RequestParam("params") String msg) {

        kafkaTemplate.send(topic, msg);
        LOGGER.info("api调用信息:{}已经推送至kafka:{}.....", msg);
    }

}
