package org.doraemon.framework.message.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.doraemon.framework.message.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2018/12/14 16:35
 */
public abstract class AbstractRocketMQMessageSender<T> implements RocketMQMessageSender<T>, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRocketMQMessageSender.class);

    @Autowired(required = false)
    private DefaultMQProducer defaultMQProducer;

    @Override
    public abstract String getNameSrvAddress();

    @Override
    public abstract String getProducerGroup();

    @Override
    public boolean send(AbstractMessage<T> rocketMQMessage) {
        final T body = rocketMQMessage.getBody();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("send message to RocketMQ start, topic: {}, tags: {}, content: {}", rocketMQMessage.getTopic(), rocketMQMessage.getTags(), JSON.toJSONString(body));
        }
        Message message = new Message(rocketMQMessage.getTopic(), rocketMQMessage.getTags(), rocketMQMessage.getKey(), new String(JSON.toJSONString(body).getBytes(), StandardCharsets.UTF_8).getBytes());
        try {
            final SendResult sendResult = this.defaultMQProducer.send(message);
            if (sendResult != null && sendResult.getSendStatus() != null && SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                LOGGER.info("the result with send message to RocketMQ is: {}", JSON.toJSONString(sendResult.getSendStatus()));
                this.onSuccess(sendResult);
            }
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            LOGGER.error("send message to RocketMQ error.", e);
            this.onFailure(e);
            return false;
        }
        return true;
    }

    @Override
    public abstract void onSuccess(SendResult sendResult);

    @Override
    public abstract void onFailure(Throwable throwable);

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.defaultMQProducer == null) {
            synchronized (this) {
                defaultMQProducer = new DefaultMQProducer(this.getProducerGroup());
                defaultMQProducer.setNamesrvAddr(this.getNameSrvAddress());
                defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));
            }
        }
        defaultMQProducer.start();
    }

    @Override
    public void destroy() {
        if (this.defaultMQProducer != null) {
            this.defaultMQProducer.shutdown();
        }
    }
}
