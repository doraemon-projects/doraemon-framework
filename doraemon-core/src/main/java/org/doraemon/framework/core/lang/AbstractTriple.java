package org.doraemon.framework.core.lang;

import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-08-08 21:43
 */
public abstract class AbstractTriple<L, M, R> implements ITriple<L, M, R> {

    @Override
    public abstract L getLeft();

    @Override
    public abstract M getMiddle();

    @Override
    public abstract R getRight();

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractTriple<?, ?, ?>) {
            final AbstractTriple<?, ?, ?> other = (AbstractTriple<?, ?, ?>) obj;
            return Objects.equals(getLeft(), other.getLeft())
                    && Objects.equals(getMiddle(), other.getMiddle())
                    && Objects.equals(getRight(), other.getRight());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (getLeft() == null ? 0 : getLeft().hashCode()) ^
                (getMiddle() == null ? 0 : getMiddle().hashCode()) ^
                (getRight() == null ? 0 : getRight().hashCode());
    }
}
