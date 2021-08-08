package org.doraemon.framework.core.lang;

import java.io.Serializable;

/**
 * @description: 三组对
 * @author: fengwenping
 * @date: 2021-08-08 16:01
 */
public interface ITriple<L, M, R> extends Serializable {

    L getLeft();

    M getMiddle();

    R getRight();
}
