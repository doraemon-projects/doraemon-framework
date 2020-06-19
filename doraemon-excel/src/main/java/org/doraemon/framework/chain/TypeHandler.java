package org.doraemon.framework.chain;

/**
 * @author yuandong.lin
 */
public interface TypeHandler<S> {

   /**
    * //TODO 待确认
    * 获取参数数据类型
    * @param obj
    * @param <T>
    * @return
    */
   <T>  S getType(T obj);

}
