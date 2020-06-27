package org.doraemon.framework.message;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 1:43
 */
public interface IMessageSender<T> {

    MessageStatus send(IMessage<T> message);

    void onSuccess(MessageStatus messageStatus);

    void onFailure(Throwable throwable);
}
