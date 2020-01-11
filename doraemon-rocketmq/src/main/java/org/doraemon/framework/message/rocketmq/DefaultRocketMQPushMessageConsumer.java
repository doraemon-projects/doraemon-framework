package org.jfteam.framework.mq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.jfteam.framework.holder.ConstantHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2018/12/14 11:29
 */
@Component("defaultRocketMQPushMessageConsumer")
public class DefaultRocketMQPushMessageConsumer implements InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRocketMQPushMessageConsumer.class);

    private Map<String, String> existTopicMap = new HashMap<>();

    @Value("${rocketMQ.server.address}")
    private String serverAddress;

    @Value("${rocketMQ.consumer.group}")
    private String consumerGroup;

    @Autowired(required = false)
    private List<RocketMQMessageListener> rocketMQMessageListeners;

    private List<DefaultMQPushConsumer> defaultMQPushConsumers = new ArrayList<>();

    @Override
    public void destroy() {
        if (!CollectionUtils.isEmpty(defaultMQPushConsumers)) {
            defaultMQPushConsumers.forEach(defaultMQPushConsumer -> defaultMQPushConsumer.shutdown());
        }
    }

    @Override
    public void afterPropertiesSet() {
        if (!CollectionUtils.isEmpty(this.rocketMQMessageListeners)) {
            synchronized (this) {
                this.rocketMQMessageListeners.forEach(rocketMQMessageListener -> {
                    final String topic = rocketMQMessageListener.getTopic();
                    final String tags = rocketMQMessageListener.getTags();
                    if (StringUtils.hasText(topic) && StringUtils.hasText(tags)) {
                        if (!this.existTopicMap.containsKey(topic)) {
                            try {
                                DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(this.consumerGroup);
                                defaultMQPushConsumer.setNamesrvAddr(this.serverAddress);
                                defaultMQPushConsumer.setInstanceName(String.valueOf(System.currentTimeMillis()));
                                defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
                                defaultMQPushConsumer.subscribe(topic, tags);
                                defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                                    String body = null;
                                    if (!CollectionUtils.isEmpty(list) && list.get(0) != null && list.get(0).getBody() != null) {
                                        try {
                                            body = new String(list.get(0).getBody(), ConstantHolder.SystemConstants.DEFAULT_CHARSET);
                                        } catch (UnsupportedEncodingException e) {
                                            LOGGER.error("rocketMQ message byte array to string error.", e);
                                        }
                                        if (StringUtils.hasText(body)) {
                                            return rocketMQMessageListener.onMessage(JSON.parseObject(body, RocketMQMessage.class));
                                        }
                                    }
                                    LOGGER.warn("rocketMQ consumer subscribe topic: {}, tags: {} failure, message content: {}", body);
                                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                });
                                defaultMQPushConsumer.start();
                                defaultMQPushConsumers.add(defaultMQPushConsumer);
                            } catch (MQClientException e) {
                                LOGGER.error("rocketMQ consumer subscribe topic: {}, tags: {} failure.", topic, tags);
                            }
                            this.existTopicMap.put(topic, tags);
                            return;
                        }
                        LOGGER.warn("the rocketMQ message listener subscribe topic: {}, tags: {} was exists.", topic, tags);
                    }
                });
            }
        }
    }
}
