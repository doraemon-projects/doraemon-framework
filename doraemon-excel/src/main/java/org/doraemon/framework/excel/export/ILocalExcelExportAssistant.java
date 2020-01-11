package org.doraemon.framework.excel.export;

import org.doraemon.framework.domain.Page;
import org.doraemon.framework.exception.ApplicationException;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: excel导出助手
 * Author:      fengwenping
 * Date:        2020/1/12 0:47
 */
public interface ILocalExcelExportAssistant {

    /**
     * 导出
     *
     * @param excelType
     * @param condition
     * @param page
     * @param parameters
     * @param <T>
     * @return
     * @throws ApplicationException
     */
    <T> int submitExportTask(String excelType, Serializable condition, Page<T> page, Map<String, Object> parameters) throws ApplicationException;
}
