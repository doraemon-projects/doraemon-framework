package org.doraemon.framework.lookup.web.model.request;

import lombok.Data;
import org.doraemon.framework.domain.PageRequest;
import org.doraemon.framework.domain.Sort;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-31 18:33
 */
@Data
public class ClassifyRequest extends PageRequest {

    public ClassifyRequest() {
        super();
    }

    public ClassifyRequest(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }

    public ClassifyRequest(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        super(pageNumber, pageSize, direction, properties);
    }

    public ClassifyRequest(int pageNumber, int pageSize, Sort sort) {
        super(pageNumber, pageSize, sort);
    }

    private String id;

    private String code;
}
