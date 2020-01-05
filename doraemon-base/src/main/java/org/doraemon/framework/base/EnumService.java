package org.doraemon.framework.base;

/**
 * 枚举公共接口
 *
 * @author : yuanDong.lin
 * @date : 2019-12-25 17:45
 */
public interface EnumService<K, V> {

    /**
     * 创建枚举公共接口
     *
     * @param k   枚举名
     * @param v   枚举值
     * @param <K> 枚举名类型
     * @param <V> 枚举值类型
     * @return EnumService 返回枚举
     */
    static <K, V> EnumService<K, V> create(K k, V v) {
        return new EnumServiceImpl<>(k, v);
    }


    /**
     * 抽象方法，子类按需实现
     *
     * @return EnumService
     */
    EnumService<K, V> getEnumServiceImpl();

    /**
     * 获取枚举key
     *
     * @return <K>枚举名
     */
    default K getKey() {
        return getEnumServiceImpl().getKey();
    }

    /**
     * 获取枚举val
     *
     * @return <V>枚举值
     */
    default V getVal() {
        return getEnumServiceImpl().getVal();
    }


}

class EnumServiceImpl<K, V> implements EnumService<K, V> {

    private final V v;

    private final K k;

    EnumServiceImpl(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public EnumService<K, V> getEnumServiceImpl() {
        return this;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getVal() {
        return v;
    }
}