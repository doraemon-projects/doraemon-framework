package org.doraemon.framework.core.util.io;

import java.util.Locale;
import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 16:09
 */
public enum FileSystem {
    GENERIC(false, false, Integer.MAX_VALUE, Integer.MAX_VALUE, new char[]{0}, new String[]{}, false),

    LINUX(true, true, 255, 4096, new char[]{
            0,
            '/'
    }, new String[]{}, false),

    MAC_OSX(true, true, 255, 1024, new char[]{
            0,
            '/',
            ':'
    }, new String[]{}, false),

    WINDOWS(false, true, 255,
            32000, new char[]{
            0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
            29, 30, 31,
            '"', '*', '/', ':', '<', '>', '?', '\\', '|'
    },
            new String[]{"AUX", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "CON", "LPT1",
                    "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9", "NUL", "PRN"}, true);

    private final boolean casePreserving;
    private final boolean caseSensitive;
    private final char[] illegalFileNameChars;
    private final int maxFileNameLength;
    private final int maxPathLength;
    private final String[] reservedFileNames;
    private final boolean supportsDriveLetter;

    FileSystem(final boolean caseSensitive, final boolean casePreserving, final int maxFileLength,
               final int maxPathLength, final char[] illegalFileNameChars, final String[] reservedFileNames,
               final boolean supportsDriveLetter) {
        this.maxFileNameLength = maxFileLength;
        this.maxPathLength = maxPathLength;
        this.illegalFileNameChars = Objects.requireNonNull(illegalFileNameChars, "illegalFileNameChars");
        this.reservedFileNames = Objects.requireNonNull(reservedFileNames, "reservedFileNames");
        this.caseSensitive = caseSensitive;
        this.casePreserving = casePreserving;
        this.supportsDriveLetter = supportsDriveLetter;
    }


    private static final boolean IS_OS_LINUX = getOsMatchesName("Linux");

    private static final boolean IS_OS_MAC = getOsMatchesName("Mac");

    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";

    private static final boolean IS_OS_WINDOWS = getOsMatchesName(OS_NAME_WINDOWS_PREFIX);

    private static boolean getOsMatchesName(final String osNamePrefix) {
        return isOsNameMatch(getSystemProperty("os.name"), osNamePrefix);
    }

    private static boolean isOsNameMatch(final String osName, final String osNamePrefix) {
        if (osName == null) {
            return false;
        }
        return osName.toUpperCase(Locale.ROOT).startsWith(osNamePrefix.toUpperCase(Locale.ROOT));
    }

    private static String getSystemProperty(final String property) {
        try {
            return System.getProperty(property);
        } catch (final SecurityException ex) {
            // we are not allowed to look at this property
            System.err.println("Caught a SecurityException reading the system property '" + property
                    + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    public boolean supportsDriveLetter() {
        return supportsDriveLetter;
    }

    public static FileSystem getCurrent() {
        if (IS_OS_LINUX) {
            return LINUX;
        }
        if (IS_OS_MAC) {
            return MAC_OSX;
        }
        if (IS_OS_WINDOWS) {
            return WINDOWS;
        }
        return GENERIC;
    }
}
