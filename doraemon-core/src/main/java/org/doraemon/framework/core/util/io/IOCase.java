package org.doraemon.framework.core.util.io;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 16:15
 */
public enum IOCase {

    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", !FilenameUtils.isSystemWindows());

    private final String name;

    private final transient boolean sensitive;

    IOCase(final String name, final boolean sensitive) {
        this.name = name;
        this.sensitive = sensitive;
    }

    public int checkIndexOf(final String str, final int strStartIndex, final String search) {
        final int endIndex = str.length() - search.length();
        if (endIndex >= strStartIndex) {
            for (int i = strStartIndex; i <= endIndex; i++) {
                if (checkRegionMatches(str, i, search)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean checkRegionMatches(final String str, final int strStartIndex, final String search) {
        return str.regionMatches(!sensitive, strStartIndex, search, 0, search.length());
    }
}
