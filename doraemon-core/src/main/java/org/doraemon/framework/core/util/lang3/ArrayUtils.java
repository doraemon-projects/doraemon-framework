package org.doraemon.framework.core.util.lang3;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 14:23
 */
public abstract class ArrayUtils {

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final int INDEX_NOT_FOUND = -1;

    public static boolean contains(final boolean[] array, final boolean valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final byte[] array, final byte valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final char[] array, final char valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final double[] array, final double valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final double[] array, final double valueToFind, final double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final float[] array, final float valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final int[] array, final int valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final long[] array, final long valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final Object[] array, final Object objectToFind) {
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(final short[] array, final short valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(final boolean[] array, final boolean valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final boolean[] array, final boolean valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final byte[] array, final byte valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final byte[] array, final byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final char[] array, final char valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final char[] array, final char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final double[] array, final double valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final double[] array, final double valueToFind, final double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance);
    }

    public static int indexOf(final double[] array, final double valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final double[] array, final double valueToFind, int startIndex, final double tolerance) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        final double min = valueToFind - tolerance;
        final double max = valueToFind + tolerance;
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final float[] array, final float valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final float[] array, final float valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final int[] array, final int valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final int[] array, final int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final long[] array, final long valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final long[] array, final long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final Object[] array, final Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    public static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final short[] array, final short valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(final short[] array, final short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean isNotEmpty(final Object array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(final Object array) {
        return getLength(array) == 0;
    }

    public static int getLength(final Object array) {
        return Objects.isNull(array) ? 0 : Array.getLength(array);
    }

    public static <T> T[] addAll(final T[] array1, @SuppressWarnings("unchecked") final T... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final Class<?> type1 = array1.getClass().getComponentType();
        @SuppressWarnings("unchecked") // OK, because array is of type T
        final T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        try {
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        } catch (final ArrayStoreException ase) {
            final Class<?> type2 = array2.getClass().getComponentType();
            if (!type1.isAssignableFrom(type2)) {
                throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), ase);
            }
            throw ase;
        }
        return joinedArray;
    }

    public static boolean[] clone(final boolean[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static byte[] clone(final byte[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static char[] clone(final char[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static double[] clone(final double[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static float[] clone(final float[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static int[] clone(final int[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static long[] clone(final long[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static short[] clone(final short[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }

    public static <T> T[] clone(final T[] array) {
        return Objects.nonNull(array) ? array.clone() : null;
    }
}
