package org.doraemon.framework.core.lang;

import java.util.Map;
import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-08-08 21:48
 */
public abstract class AbstractPair<K, V> implements IPair<K, V>, Map.Entry<K, V> {

    protected K key;
    protected V value;

    public AbstractPair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final K getKey() {
        return this.key;
    }

    @Override
    public final V getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry<?, ?>) {
            final Map.Entry<?, ?> other = (Map.Entry<?, ?>) obj;
            return Objects.equals(getKey(), other.getKey())
                    && Objects.equals(getValue(), other.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (getKey() == null ? 0 : getKey().hashCode()) ^
                (getValue() == null ? 0 : getValue().hashCode());
    }

    @Override
    public String toString() {
        return "(" + getKey() + ',' + getValue() + ')';
    }
}
