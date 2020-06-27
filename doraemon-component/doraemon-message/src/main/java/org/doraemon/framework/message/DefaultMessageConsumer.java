package org.doraemon.framework.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-25 17:57
 */
public class DefaultMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalMessageConsumer.class);

    private List<IMessageListener> messageListeners;

    public void setMessageListeners(List<IMessageListener> messageListeners) {
        this.messageListeners = messageListeners;
    }

    private Map<String, IMessageListener> messageListenerMap = new ConcurrentHashMap<>();

    public void init() {
        if (Objects.nonNull(messageListeners) && messageListeners.size() > 0) {
            messageListeners.stream().forEach(listener -> {
                if (Objects.nonNull(messageListenerMap.containsKey(listener.getTopic()))) {
                    LOGGER.warn("A message listener with a topic of '{}' already exists", listener.getTopic());
                }
                messageListenerMap.put(listener.getTopic(), listener);
                Consumer consumer = new Consumer(listener.getTopic());
                consumer.run();
            });
        }
    }

    private void consume(String topic, IMessage message) {
        final IMessageListener messageListener = messageListenerMap.get(topic);
        if (Objects.nonNull(messageListener)) {
            messageListener.onMessage(message);
            return;
        }
        LOGGER.error("There is no message listener with a topic of '{}'", topic);
    }

    public class Consumer implements Runnable {

        private String topic;

        public Consumer(String topic) {
            this.topic = topic;
        }

        @Override
        public void run() {
            final BlockingQueue<IMessage> messageBlockingQueue = LocalMessageHolder.getMessageBlockingQueue(this.topic);
            while (messageBlockingQueue.isEmpty()) {
                try {
                    Thread.sleep(3000L);
                    final IMessage message = messageBlockingQueue.take();
                    if (Objects.nonNull(message)) {
                        consume(message.getTopic(), message);
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("Consumer thread sleep failure", e);
                }
            }
        }
    }
}
