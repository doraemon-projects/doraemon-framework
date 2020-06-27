package org.doraemon.framework.context;

import org.doraemon.framework.chain.TypeHandler;

/**
 * excel上下文对象
 */
public class ExcelAnalysisContext {

    private TypeHandler<?> typeHandler;

    public TypeHandler<?> getTypeHandler() {
        return typeHandler;
    }

    public void setTypeHandler(TypeHandler<?> typeHandler) {
        this.typeHandler = typeHandler;
    }
}
