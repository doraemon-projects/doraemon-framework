package org.doraemon.framework.core.lang;

/**
 * @description: 可变的三组对
 * @author: fengwenping
 * @date: 2021-08-08 16:11
 */
public class MutableTriple<L, M, R> extends AbstractTriple<L, M, R> {

    private L left;
    private M middle;
    private R right;

    public MutableTriple(final L left, final M middle, final R right) {
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

    public void setLeft(L left) {
        this.left = left;
    }

    public void setMiddle(M middle) {
        this.middle = middle;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public static <L, M, R> MutableTriple<L, M, R> of(final L left, final M middle, final R right) {
        return new MutableTriple<>(left, middle, right);
    }
}
