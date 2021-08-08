package org.doraemon.framework.core.lang;

/**
 * @description: 不可变的三组对
 * @author: fengwenping
 * @date: 2021-08-08 16:08
 */
public class ImmutableTriple<L, M, R> extends AbstractTriple<L, M, R> {

    private L left;
    private M middle;
    private R right;

    public ImmutableTriple(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    @Override
    public L getLeft() {
        return this.left;
    }

    @Override
    public M getMiddle() {
        return this.middle;
    }

    @Override
    public R getRight() {
        return this.right;
    }

    public static <L, M, R> ImmutableTriple<L, M, R> of(final L left, final M middle, final R right) {
        return new ImmutableTriple<>(left, middle, right);
    }
}
