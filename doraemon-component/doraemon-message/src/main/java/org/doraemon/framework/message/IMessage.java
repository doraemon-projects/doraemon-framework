package org.doraemon.framework.message;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 2:36
 */
public interface IMessage<T> extends Serializable {

    String getTopic();

    String getTags();

    String getKey();

    T getBody();
}
