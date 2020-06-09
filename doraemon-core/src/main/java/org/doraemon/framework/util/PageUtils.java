package org.doraemon.framework.util;

import com.github.pagehelper.PageInfo;
import org.doraemon.framework.domain.Page;
import org.doraemon.framework.domain.PageImpl;
import org.doraemon.framework.domain.PageRequest;

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
     * @return org.doraemon.framework.domain.PageImpl 转换后的PageImpl对象
     */
    public static <T, K> Page<K> build(Page<T> source, Function<T, K> function) {
        List<T> content = source.getContent();
        PageRequest pageRequest = new PageRequest(source.getNumber(), source.getSize(), source.getSort());
        List<K> result = content.stream().map(function).collect(Collectors.toList());
        return new PageImpl<>(result, pageRequest);
    }

    /**
     * PageHelper 分页对象转换
     *
     * @param source   源PageInfo对象
     * @param function 转换函数
     * @param <T>      源PageInfo对象数据类型
     * @param <K>      转换后的数据对类型
     * @return com.github.pagehelper.PageInfo 转换后的PageInfo对象
     */
    public static <T, K> PageInfo<K> build(PageInfo<T> source, Function<T, K> function) {
        List<T> list = source.getList();
        List<K> result = list.stream().map(function).collect(Collectors.toList());
        PageInfo<K> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(source.getPageSize());
        pageInfo.setPageNum(source.getPageNum());
        pageInfo.setList(result);
        return pageInfo;
    }
}
