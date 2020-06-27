package org.doraemon.framework.excel.export;

import java.util.List;
import java.util.Map;

/**
 * @author yuandong.lin
 */
public interface IExcelExportConfigProvider<T> extends IExcelDataProvider<T>{

    /**
     * 获取excel基础配置信息(head)
     * @param excelType 导出excel类型
     * @param filters 筛选条件
     * @return excel配置信息
     */
     List<T> getExcelConfigInfo(String excelType, Map<String, Object> filters);
}
