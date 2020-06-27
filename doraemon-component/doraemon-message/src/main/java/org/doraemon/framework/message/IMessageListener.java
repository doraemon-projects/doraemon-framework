package org.doraemon.framework.message;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 1:45
 */
public interface IMessageListener<T> {

    String getTopic();

    String getTags();

    MessageStatus onMessage(IMessage<T> message);
}
