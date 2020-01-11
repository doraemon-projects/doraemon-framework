package org.doraemon.framework.message;

import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 2:51
 */
@Named(value = "localMessageSender")
public class LocalMessageSender implements IMessageSender {
    @Override
    public MessageStatus send(IMessage message) {
        return null;
    }

    @Override
    public void onSuccess(MessageStatus messageStatus) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
