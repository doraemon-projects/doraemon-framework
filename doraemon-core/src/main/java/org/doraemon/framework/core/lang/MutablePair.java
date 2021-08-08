package org.doraemon.framework.core.lang;

/**
 * @description: 可变的键值对
 * @author: fengwenping
 * @date: 2021-08-08 16:14
 */
public class MutablePair<K, V> extends AbstractPair<K, V> {

    public MutablePair(final K key, final V value) {
        super(key, value);
    }

    public void setKey(final K key) {
        this.key = key;
    }

    @Override
    public V setValue(final V value) {
        final V result = getValue();
        this.value = value;
        return result;
    }
}
