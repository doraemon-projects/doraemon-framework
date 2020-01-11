package org.doraemon.framework.message;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 2:12
 */
public class AbstractMessage<T> implements IMessage {

    private String topic;

    private String tags;

    private String key;

    private T body;

    @Override
    public String getTopic() {
        return this.topic;
    }

    @Override
    public String getTags() {
        return this.tags;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public T getBody() {
        return this.body;
    }

    private AbstractMessage(Builder<T> builder) {
        topic = builder.topic;
        tags = builder.tags;
        key = builder.key;
        body = builder.body;
    }


    public static final class Builder<T> {
        private String topic;
        private String tags;
        private String key;
        private T body;

        public Builder() {
        }

        public Builder topic(String val) {
            topic = val;
            return this;
        }

        public Builder tags(String val) {
            tags = val;
            return this;
        }

        public Builder key(String val) {
            key = val;
            return this;
        }

        public Builder body(T val) {
            body = val;
            return this;
        }

        public AbstractMessage build() {
            return new AbstractMessage(this);
        }
    }
}
