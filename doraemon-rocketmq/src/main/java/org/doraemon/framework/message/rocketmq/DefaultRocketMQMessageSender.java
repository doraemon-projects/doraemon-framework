package org.jfteam.framework.mq;

import org.apache.rocketmq.client.producer.SendResult;
import org.doraemon.framework.message.IMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 消息发送组件
 * @author: fengwenping
 * @date: 2018/12/14 16:36
 */
@Component("defaultRocketMQMessageSender")
public class DefaultRocketMQMessageSender extends IMessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRocketMQMessageSender.class);

    @Value("${rocketMQ.server.address}")
    private String serverAddress;

    @Value("${rocketMQ.producer.group}")
    private String producerGroup;

    @Override
    public String getNameSrvAddress() {
        return this.serverAddress;
    }

    @Override
    public String getProducerGroup() {
        return this.producerGroup;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        LOGGER.info("发送消息成功");
    }

    @Override
    public void onFailure(Throwable throwable) {
        LOGGER.error("发送消息异常.", throwable);
    }
}
