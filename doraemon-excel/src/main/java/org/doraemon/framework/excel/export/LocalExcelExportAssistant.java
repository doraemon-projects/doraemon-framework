package org.doraemon.framework.excel.export;

import org.doraemon.framework.domain.Page;
import org.doraemon.framework.exception.ApplicationException;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 1:01
 */
@Named
public class LocalExcelExportAssistant implements ILocalExcelExportAssistant {
    @Override
    public <T> int submitExportTask(String excelType, Serializable condition, Page<T> page, Map<String, Object> parameters) throws ApplicationException {
        IExcelDataProvider<T> excelDataProvider = new IExcelDataProvider<T>() {
            @Override
            public List<T> getBatchData(Serializable condition, Page<T> page) throws ApplicationException {
                return null;
            }
        };
        final List<T> data = excelDataProvider.getBatchData(condition, page);
        return 0;
    }
}
