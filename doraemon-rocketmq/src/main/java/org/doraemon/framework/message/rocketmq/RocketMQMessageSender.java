package org.doraemon.framework.message.rocketmq;

import org.doraemon.framework.message.IMessageSender;

/**
 * @description: RocketMQ消息发送类
 * @author: fengwenping
 * @date: 2018/12/14 16:41
 */
public interface RocketMQMessageSender<T> extends IMessageSender {

    String getNameSrvAddress();

    String getProducerGroup();
}
