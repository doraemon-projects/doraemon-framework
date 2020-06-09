package org.doraemon.framework.excel.export;

import org.doraemon.framework.domain.Page;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.message.IMessageSender;

import javax.inject.Inject;
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

    @Inject
    private IMessageSender<List> messageSender;

    @Override
    public <T> int submitExportTask(String excelType, Serializable condition, Page<T> page, Map<String, Object> parameters) throws ApplicationException {
        IExcelDataProvider<T> excelDataProvider = (filter, date) -> null;
        final List<T> data = excelDataProvider.getBatchData(condition, page);
        return 0;
    }
}
