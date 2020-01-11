package org.doraemon.framework.excel.export;

import org.doraemon.framework.domain.Page;
import org.doraemon.framework.exception.ApplicationException;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 1:08
 */
public interface IExcelDataProvider<T> {

    List<T> getBatchData(Serializable condition, Page<T> page) throws ApplicationException;
}
