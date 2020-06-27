package org.doraemon.framework.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 2:51
 */
@Named(value = "defaultMessageSender")
public class DefaultMessageSender implements IMessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalMessageSender.class);

    @Override
    public MessageStatus send(IMessage message) {
        try {
            LocalMessageHolder.getMessageBlockingQueue(message.getTopic()).put(message);
        } catch (InterruptedException e) {
            LOGGER.error("send local message failure.", e);
            this.onFailure(e);
            return MessageStatus.FAILURE;
        }
        this.onSuccess(MessageStatus.SUCCESS);
        return MessageStatus.SUCCESS;
    }

    @Override
    public void onSuccess(MessageStatus messageStatus) {
        LOGGER.info("send local message success");
    }

    @Override
    public void onFailure(Throwable throwable) {
        LOGGER.error("send local message failure", throwable);
    }
}
