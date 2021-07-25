package org.doraemon.framework.core.util;

import org.doraemon.framework.core.base.page.Page;
import org.doraemon.framework.core.base.page.PageParam;
import org.doraemon.framework.core.base.page.PageResult;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtils {

    private PageUtils() {
    }

    /**
     * 自定义分页对象转换
     *
     * @param source   源page对象
     * @param function 转换函数
     * @param <T>      源page对象数据类型
     * @param <K>      转换后的数据对类型
     * @return org.doraemon.framework.core.base.page.PageResult 转换后的PageImpl对象
     */
    public static <T, K> Page<K> build(Page<T> source, Function<T, K> function) {
        List<T> content = source.getContent();
        PageParam pageParam = new PageParam(source.getNumber(), source.getSize(), source.getSort());
        List<K> result = content.stream().map(function).collect(Collectors.toList());
        return new PageResult<>(result, pageParam);
    }
}
