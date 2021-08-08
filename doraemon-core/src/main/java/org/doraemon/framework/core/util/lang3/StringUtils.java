package org.doraemon.framework.core.util.lang3;

import org.doraemon.framework.core.util.collection.CollectionUtils;

import java.util.*;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 13:46
 */
public abstract class StringUtils {

    private static final int STRING_BUILDER_SIZE = 256;

    public static final String EMPTY = "";

    public static final int INDEX_NOT_FOUND = -1;

    public static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        // Step-wise comparison
        final int length = cs1.length();
        for (int i = 0; i < length; i++) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static String join(final byte[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final byte[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String join(final char[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final char[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String join(final double[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final double[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String join(final float[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final float[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String join(final int[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final int[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String join(final Iterable<?> iterable, final char separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(final Iterator<?> iterator, final char separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, EMPTY);
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(STRING_BUILDER_SIZE); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

    public static String join(final Iterator<?> iterator, final String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(STRING_BUILDER_SIZE); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(final List<?> list, final char separator, final int startIndex, final int endIndex) {
        if (list == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final List<?> subList = list.subList(startIndex, endIndex);
        return join(subList.iterator(), separator);
    }

    public static String join(final List<?> list, final String separator, final int startIndex, final int endIndex) {
        if (list == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final List<?> subList = list.subList(startIndex, endIndex);
        return join(subList.iterator(), separator);
    }

    public static String join(final long[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final long[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    public static String join(final Object[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final Object[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        if (array[startIndex] != null) {
            buf.append(array[startIndex]);
        }
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(final Object[] array, final String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final Object[] array, String separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        final StringBuilder buf = new StringBuilder(noOfItems * 16);

        if (array[startIndex] != null) {
            buf.append(array[startIndex]);
        }

        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(final short[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(final short[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        buf.append(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);
            buf.append(array[i]);
        }
        return buf.toString();
    }

    @SafeVarargs
    public static <T> String join(final T... elements) {
        return join(elements, null);
    }

    public static String[] split(final String str) {
        return split(str, null, -1);
    }

    public static String[] split(final String str, final char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(final String str, final String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(final String str, final String separatorChars, final int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    private static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    private static String[] splitWorker(final String str, final String separatorChars, final int max, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String replace(final String text, final String searchString, final String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(final String text, final String searchString, final String replacement, final int max) {
        return replace(text, searchString, replacement, max, false);
    }

    private static String replace(final String text, String searchString, final String replacement, int max, final boolean ignoreCase) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        if (ignoreCase) {
            searchString = searchString.toLowerCase();
        }
        int start = 0;
        int end = ignoreCase ? indexOfIgnoreCase(text, searchString, start) : indexOf(text, searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        final int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase < 0 ? 0 : increase;
        increase *= max < 0 ? 16 : max > 64 ? 64 : max;
        final StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text, start, end).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = ignoreCase ? indexOfIgnoreCase(text, searchString, start) : indexOf(text, searchString, start);
        }
        buf.append(text, start, text.length());
        return buf.toString();
    }

    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (startPos < 0) {
            startPos = 0;
        }
        final int endLimit = str.length() - searchStr.length() + 1;
        if (startPos > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return startPos;
        }
        for (int i = startPos; i < endLimit; i++) {
            if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos) {
        if (seq == null || searchSeq == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.indexOf(seq, searchSeq, startPos);
    }

    public static String capitalize(final String str) {
        final int strLen = length(str);
        if (strLen == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, (String) null);
    }

    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        } else if (delimiter == null) {
            return new String[]{str};
        } else {
            List<String> result = new ArrayList();
            int pos;
            if (delimiter.isEmpty()) {
                for (pos = 0; pos < str.length(); ++pos) {
                    result.add(deleteAny(str.substring(pos, pos + 1), charsToDelete));
                }
            } else {
                int delPos;
                for (pos = 0; (delPos = str.indexOf(delimiter, pos)) != -1; pos = delPos + delimiter.length()) {
                    result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                }

                if (str.length() > 0 && pos <= str.length()) {
                    result.add(deleteAny(str.substring(pos), charsToDelete));
                }
            }

            return toStringArray((Collection) result);
        }
    }

    public static String deleteAny(String inString, String charsToDelete) {
        if (isNotEmpty(inString) && isNotEmpty(charsToDelete)) {
            StringBuilder sb = new StringBuilder(inString.length());

            for (int i = 0; i < inString.length(); ++i) {
                char c = inString.charAt(i);
                if (charsToDelete.indexOf(c) == -1) {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return inString;
        }
    }

    public static String[] toStringArray(Collection<String> collection) {
        return !CollectionUtils.isEmpty(collection) ? (String[]) collection.toArray(ArrayUtils.EMPTY_STRING_ARRAY) : ArrayUtils.EMPTY_STRING_ARRAY;
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delimited) {
        return collectionToDelimitedString(coll, delimited, "", "");
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delimited, String prefix, String suffix) {
        if (CollectionUtils.isEmpty(coll)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator it = coll.iterator();

            while (it.hasNext()) {
                sb.append(prefix).append(it.next()).append(suffix);
                if (it.hasNext()) {
                    sb.append(delimited);
                }
            }

            return sb.toString();
        }
    }

    public static String collectionToCommaDelimitedString(Collection<?> coll) {
        return collectionToDelimitedString(coll, ",");
    }

    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
        if (str == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        } else {
            StringTokenizer st = new StringTokenizer(str, delimiters);
            ArrayList tokens = new ArrayList();

            while (true) {
                String token;
                do {
                    if (!st.hasMoreTokens()) {
                        return toStringArray((Collection) tokens);
                    }

                    token = st.nextToken();
                    if (trimTokens) {
                        token = token.trim();
                    }
                } while (ignoreEmptyTokens && token.length() <= 0);

                tokens.add(token);
            }
        }
    }

    public static boolean isNumeric(CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        } else {
            int sz = cs.length();
            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}
