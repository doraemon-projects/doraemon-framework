package org.doraemon.framework.chain;

/**
 * @author ayg
 */
public class DefaultTypeHandler implements TypeHandler<String>{

    @Override
    public <T> String getType(T obj) {
        return obj instanceof String ? "string" : "";
    }
}
