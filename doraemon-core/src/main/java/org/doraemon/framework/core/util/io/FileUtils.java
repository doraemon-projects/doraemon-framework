package org.doraemon.framework.core.util.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @description: 文件操作工具类, 参考apache io.FileUtils
 * @author: fengwenping
 * @date: 2021-07-11 18:16
 */
public abstract class FileUtils {

    public static void deleteDirectory(final File directory) throws IOException {
        Objects.requireNonNull(directory, "directory");
        if (!directory.exists()) {
            return;
        }
        if (!isSymlink(directory)) {
            cleanDirectory(directory);
        }
        delete(directory);
    }

    public static boolean isSymlink(final File file) {
        return file != null && Files.isSymbolicLink(file.toPath());
    }

    public static File delete(final File file) throws IOException {
        Objects.requireNonNull(file, "file");
        Files.delete(file.toPath());
        return file;
    }

    public static void cleanDirectory(final File directory) throws IOException {
        final File[] files = listFiles(directory, null);
        final List<Exception> causeList = new ArrayList<>();
        for (final File file : files) {
            try {
                forceDelete(file);
            } catch (final IOException ioe) {
                causeList.add(ioe);
            }
        }
        if (!causeList.isEmpty()) {
            throw new IOException(directory.toString(), causeList == null ? null : causeList.get(0));
        }
    }

    public static void forceDelete(final File file) throws IOException {
        Objects.requireNonNull(file, "file");
        try {
            if (Files.isDirectory(file.toPath(), new LinkOption[]{})) {
                deleteDirectory(file);
            } else {
                delete(file);
            }
        } catch (final IOException e) {
            throw new IOException("Cannot delete file: " + file, e);
        }
    }

    private static File[] listFiles(final File directory, final FileFilter fileFilter) throws IOException {
        requireDirectoryExists(directory, "directory");
        final File[] files = fileFilter == null ? directory.listFiles() : directory.listFiles(fileFilter);
        if (files == null) {
            throw new IOException("Unknown I/O error listing contents of directory: " + directory);
        }
        return files;
    }

    public static void copyDirectory(final File srcDir, final File destDir) throws IOException {
        copyDirectory(srcDir, destDir, true);
    }

    public static void copyDirectory(final File srcDir, final File destDir, final boolean preserveFileDate)
            throws IOException {
        copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static void copyDirectory(final File srcDir, final File destDir, final FileFilter filter) throws IOException {
        copyDirectory(srcDir, destDir, filter, true);
    }

    public static void copyDirectory(final File srcDir, final File destDir, final FileFilter filter, final boolean preserveFileDate) throws IOException {
        copyDirectory(srcDir, destDir, filter, preserveFileDate, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyDirectory(final File srcDir, final File destDir, final FileFilter fileFilter,
                                     final boolean preserveFileDate, final CopyOption... copyOptions) throws IOException {
        Objects.requireNonNull(srcDir, "source");
        if (!srcDir.exists()) {
            throw new FileNotFoundException("File system element for parameter '" + "source" + "' does not exist: '" + srcDir + "'");
        }
        if (!srcDir.isDirectory()) {
            throw new IllegalArgumentException("Parameter '" + "source" + "' is not a directory: '" + srcDir + "'");
        }
        Objects.requireNonNull(destDir, "destination");
        requireCanonicalPathsNotEquals(srcDir, destDir);

        // Cater for destination being directory within the source directory (see IO-141)
        List<String> exclusionList = null;
        final String srcDirCanonicalPath = srcDir.getCanonicalPath();
        final String destDirCanonicalPath = destDir.getCanonicalPath();
        if (destDirCanonicalPath.startsWith(srcDirCanonicalPath)) {
            final File[] srcFiles = listFiles(srcDir, fileFilter);
            if (srcFiles != null && srcFiles.length > 0) {
                exclusionList = new ArrayList<>(srcFiles.length);
                for (final File srcFile : srcFiles) {
                    final File copiedFile = new File(destDir, srcFile.getName());
                    exclusionList.add(copiedFile.getCanonicalPath());
                }
            }
        }
        doCopyDirectory(srcDir, destDir, fileFilter, exclusionList, preserveFileDate, preserveFileDate ? addCopyAttributes(copyOptions) : copyOptions);
    }

    private static void doCopyDirectory(final File srcDir, final File destDir, final FileFilter fileFilter,
                                        final List<String> exclusionList, final boolean preserveDirDate, final CopyOption... copyOptions) throws IOException {
        // recurse dirs, copy files.
        final File[] srcFiles = listFiles(srcDir, fileFilter);
        requireDirectoryIfExists(destDir, "destDir");
        mkdirs(destDir);
        requireCanWrite(destDir, "destDir");
        for (final File srcFile : srcFiles) {
            final File dstFile = new File(destDir, srcFile.getName());
            if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath())) {
                if (srcFile.isDirectory()) {
                    doCopyDirectory(srcFile, dstFile, fileFilter, exclusionList, preserveDirDate, copyOptions);
                } else {
                    copyFile(srcFile, dstFile, copyOptions);
                }
            }
        }
        // Do this last, as the above has probably affected directory metadata
        if (preserveDirDate) {
            final long timeMillis = lastModified(srcDir);
            if (!destDir.setLastModified(timeMillis)) {
                throw new IOException(String.format("Failed setLastModified(%s) on '%s'", timeMillis, destDir));
            }
        }
    }

    public static long lastModified(final File file) throws IOException {
        return Files.getLastModifiedTime(Objects.requireNonNull(file.toPath(), "file")).toMillis();
    }

    private static File requireDirectoryIfExists(final File directory, final String name) {
        Objects.requireNonNull(directory, name);
        if (directory.exists()) {
            requireDirectory(directory, name);
        }
        return directory;
    }

    private static File requireDirectory(final File directory, final String name) {
        Objects.requireNonNull(directory, name);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'");
        }
        return directory;
    }

    private static File requireDirectoryExists(final File directory, final String name) {
        Objects.requireNonNull(directory, name);
        if (!directory.exists()) {
            throw new IllegalArgumentException("File system element for parameter '" + name + "' does not exist: '" + directory + "'");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'");
        }
        return directory;
    }

    public static boolean deleteQuietly(final File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (final Exception ignored) {
        }
        try {
            return file.delete();
        } catch (final Exception ignored) {
            return false;
        }
    }

    public static String readFileToString(final File file, final Charset charsetName) throws IOException {
        Objects.requireNonNull(file, "file");
        try (InputStream inputStream = new FileInputStream(file)) {
            return IOUtils.toString(inputStream, charsetName);
        }
    }

    public static String readFileToString(final File file, final String charsetName) throws IOException {
        return readFileToString(file, Charset.forName(charsetName));
    }

    public static void copyFile(final File srcFile, final File destFile) throws IOException {
        copyFile(srcFile, destFile, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(final File srcFile, final File destFile, final boolean preserveFileDate) throws IOException {
        copyFile(srcFile, destFile, preserveFileDate
                ? new CopyOption[]{StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING}
                : new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
    }

    public static void copyFile(final File srcFile, final File destFile, final boolean preserveFileDate, final CopyOption... copyOptions)
            throws IOException {
        copyFile(srcFile, destFile, preserveFileDate ? addCopyAttributes(copyOptions) : copyOptions);
    }

    private static CopyOption[] addCopyAttributes(final CopyOption... copyOptions) {
        // Make a copy first since we don't want to sort the call site's version.
        final CopyOption[] actual = Arrays.copyOf(copyOptions, copyOptions.length + 1);
        Arrays.sort(actual, 0, copyOptions.length);
        if (Arrays.binarySearch(copyOptions, 0, copyOptions.length, StandardCopyOption.COPY_ATTRIBUTES) >= 0) {
            return copyOptions;
        }
        actual[actual.length - 1] = StandardCopyOption.COPY_ATTRIBUTES;
        return actual;
    }

    public static void copyFile(final File srcFile, final File destFile, final CopyOption... copyOptions) throws IOException {
        Objects.requireNonNull(srcFile, "source");
        if (!srcFile.exists()) {
            throw new FileNotFoundException("File system element for parameter srcFile does not exist: '" + srcFile + "'");
        }
        Objects.requireNonNull(destFile, "destination");
        requireCanonicalPathsNotEquals(srcFile, destFile);
        createParentDirectories(destFile);
        requireFileIfExists(destFile, "destFile");
        if (destFile.exists()) {
            requireCanWrite(destFile, "destFile");
        }

        // On Windows, the last modified time is copied by default.
        Files.copy(srcFile.toPath(), destFile.toPath(), copyOptions);
        requireEqualSizes(srcFile, destFile, srcFile.length(), destFile.length());
    }

    public static void writeStringToFile(final File file, final String data, final Charset charset)
            throws IOException {
        writeStringToFile(file, data, charset, false);
    }

    public static void writeStringToFile(final File file, final String data, final Charset charset, final boolean append) throws IOException {
        try (OutputStream out = openOutputStream(file, append)) {
            IOUtils.write(data, out, charset);
        }
    }

    public static void writeStringToFile(final File file, final String data, final String charsetName) throws IOException {
        writeStringToFile(file, data, charsetName, false);
    }

    public static void writeStringToFile(final File file, final String data, final String charsetName, final boolean append) throws IOException {
        writeStringToFile(file, data, Charset.forName(charsetName), append);
    }

    public static File createParentDirectories(final File file) throws IOException {
        return mkdirs(getParentFile(file));
    }

    public static FileOutputStream openOutputStream(final File file, final boolean append) throws IOException {
        Objects.requireNonNull(file, "file");
        if (file.exists()) {
            requireFile(file, "file");
            requireCanWrite(file, "file");
        } else {
            createParentDirectories(file);
        }
        return new FileOutputStream(file, append);
    }

    public static void forceMkdir(final File directory) throws IOException {
        mkdirs(directory);
    }

    public static void write(final File file, final CharSequence data, final Charset charset) throws IOException {
        write(file, data, charset, false);
    }

    public static void write(final File file, final CharSequence data, final Charset charset, final boolean append) throws IOException {
        writeStringToFile(file, Objects.toString(data, null), charset, append);
    }

    // Private method, must be invoked will a directory parameter
    public static void write(final File file, final CharSequence data, final String charsetName) throws IOException {
        write(file, data, charsetName, false);
    }

    public static void write(final File file, final CharSequence data, final String charsetName, final boolean append)
            throws IOException {
        write(file, data, Charset.forName(charsetName), append);
    }

    /**
     * 校验两个文件是否相同
     *
     * @param file1
     * @param file2
     * @throws IOException
     */
    private static void requireCanonicalPathsNotEquals(final File file1, final File file2) throws IOException {
        final String canonicalPath = file1.getCanonicalPath();
        if (canonicalPath.equals(file2.getCanonicalPath())) {
            throw new IllegalArgumentException(String.format("File canonical paths are equal: '%s' (file1='%s', file2='%s')", canonicalPath, file1, file2));
        }
    }

    private static File getParentFile(final File file) {
        return file == null ? null : file.getParentFile();
    }

    private static File mkdirs(final File directory) throws IOException {
        if ((directory != null) && (!directory.mkdirs() && !directory.isDirectory())) {
            throw new IOException("Cannot create directory '" + directory + "'.");
        }
        return directory;
    }

    private static File requireFileIfExists(final File file, final String name) {
        Objects.requireNonNull(file, name);
        return file.exists() ? requireFile(file, name) : file;
    }

    private static File requireFile(final File file, final String name) {
        Objects.requireNonNull(file, name);
        if (!file.isFile()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a file: " + file);
        }
        return file;
    }

    private static void requireCanWrite(final File file, final String name) {
        Objects.requireNonNull(file, "file");
        if (!file.canWrite()) {
            throw new IllegalArgumentException("File parameter '" + name + " is not writable: '" + file + "'");
        }
    }

    private static void requireEqualSizes(final File srcFile, final File destFile, final long srcLen, final long dstLen) throws IOException {
        if (srcLen != dstLen) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "' Expected length: " + srcLen + " Actual: " + dstLen);
        }
    }

}
