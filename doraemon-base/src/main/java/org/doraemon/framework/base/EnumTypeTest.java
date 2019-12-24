package org.doraemon.framework.base;

/**
 * @author THINKPAD
 */
public interface EnumTypeTest<T,K> {

    static <T, K> EnumTypeTest create(T t, K k){
        return new EnumTypeTestImpl<T,K>(t,k);
    };


    EnumTypeTest<T,K> getDelegate();

    default T getKey() {
        return getDelegate().getKey();
    }

    default K getVal() {
        return getDelegate().getVal();
    }



}
class EnumTypeTestImpl<T,K> implements EnumTypeTest<T,K> {

    private T t;

    private K k;

    public EnumTypeTestImpl(T t, K k) {
        this.t = t;
        this.k = k;
    }

    @Override
    public EnumTypeTest<T,K> getDelegate() {
        return this;
    }

    @Override
    public T getKey() {
        return t;
    }

    @Override
    public K getVal() {
        return k;
    }
}