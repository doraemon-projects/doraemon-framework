package org.doraemon.framework.message;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 本地消息持有者
 * @author: fengwenping
 * @date: 2020-04-25 17:42
 */
public final class LocalMessageHolder {

    private LocalMessageHolder() {
    }

    private static Map<String, BlockingQueue<IMessage>> MESSAGE_BLOCKING_QUEUE_MAP = new ConcurrentHashMap<>(10);

    public static BlockingQueue<IMessage> getMessageBlockingQueue(String topic) {
        BlockingQueue<IMessage> messageBlockingQueue = MESSAGE_BLOCKING_QUEUE_MAP.get(topic);
        if (Objects.nonNull(messageBlockingQueue)) {
            return messageBlockingQueue;
        } else {
            messageBlockingQueue = MESSAGE_BLOCKING_QUEUE_MAP.get(topic);
            if (Objects.isNull(messageBlockingQueue)) {
                synchronized (LocalMessageHolder.class) {
                    messageBlockingQueue = new ArrayBlockingQueue<>(1000);
                    MESSAGE_BLOCKING_QUEUE_MAP.put(topic, messageBlockingQueue);
                }
            }
        }
        return messageBlockingQueue;
    }
}
