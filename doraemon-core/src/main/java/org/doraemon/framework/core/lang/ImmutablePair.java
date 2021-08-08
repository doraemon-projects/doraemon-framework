package org.doraemon.framework.core.lang;

/**
 * @description: 不可变的键值对
 * @author: fengwenping
 * @date: 2021-08-08 16:13
 */
public class ImmutablePair<K, V> extends AbstractPair<K, V> {
    public ImmutablePair(final K key, final V value) {
        super(key, value);
    }

    @Override
    public V setValue(final V value) {
        throw new UnsupportedOperationException();
    }
}
