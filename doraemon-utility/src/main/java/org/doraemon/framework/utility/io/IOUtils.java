package org.doraemon.framework.utility.io;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-11 16:38
 */
public abstract class IOUtils {

    public static final int EOF = -1;

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static final byte[] EMPTY_BYTE_ARRAY = {};

    private static final ThreadLocal<byte[]> SKIP_BYTE_BUFFER = ThreadLocal.withInitial(IOUtils::byteArray);

    private static final ThreadLocal<char[]> SKIP_CHAR_BUFFER = ThreadLocal.withInitial(IOUtils::charArray);

    public static void close(final Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    public static void close(final Closeable... closeables) throws IOException {
        if (closeables != null) {
            for (final Closeable closeable : closeables) {
                close(closeable);
            }
        }
    }

    public static void close(final Closeable closeable, final Consumer<IOException> consumer) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (final IOException e) {
                if (consumer != null) {
                    consumer.accept(e);
                }
            }
        }
    }

    public static void close(final URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

    public static void closeQuietly(final Closeable closeable) {
        closeQuietly(closeable, (Consumer<IOException>) null);
    }

    public static void closeQuietly(final Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (final Closeable closeable : closeables) {
            closeQuietly(closeable);
        }
    }

    public static void closeQuietly(final Closeable closeable, final Consumer<IOException> consumer) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (final IOException e) {
                if (consumer != null) {
                    consumer.accept(e);
                }
            }
        }
    }

    public static void closeQuietly(final InputStream input) {
        closeQuietly((Closeable) input);
    }

    public static void closeQuietly(final OutputStream output) {
        closeQuietly((Closeable) output);
    }

    public static void closeQuietly(final Reader reader) {
        closeQuietly((Closeable) reader);
    }

    public static void closeQuietly(final Selector selector) {
        closeQuietly((Closeable) selector);
    }

    public static void closeQuietly(final ServerSocket serverSocket) {
        closeQuietly((Closeable) serverSocket);
    }

    public static void closeQuietly(final Socket socket) {
        closeQuietly((Closeable) socket);
    }

    public static void closeQuietly(final Writer writer) {
        closeQuietly((Closeable) writer);
    }

    public static String toString(final byte[] input, final String charsetName) throws IOException {
        return new String(input, Charset.forName(charsetName));
    }

    public static String toString(final InputStream inputStream, final Charset charset) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
             ByteArrayOutputStream buf = new ByteArrayOutputStream();) {
            int result = bis.read();
            while (result != EOF) {
                buf.write((byte) result);
                result = bis.read();
            }
            return buf.toString();
        }
    }

    public static String toString(final InputStream input, final String charsetName) throws IOException {
        return toString(input, Charset.forName(charsetName));
    }

    public static String toString(final Reader reader) throws IOException {
        try (final StringWriter sw = new StringWriter()) {
            char arr[] = charArray();
            int n;
            while (EOF != (n = reader.read(arr))) {
                sw.write(arr, 0, n);
            }
            return sw.toString();
        }
    }

    public static String toString(final URI uri, final Charset encoding) throws IOException {
        return toString(uri.toURL(), encoding);
    }

    public static String toString(final URI uri, final String charsetName) throws IOException {
        return toString(uri, Charset.forName(charsetName));
    }

    public static String toString(final URL url, final Charset encoding) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            return toString(inputStream, encoding);
        }
    }

    public static String toString(final URL url, final String charsetName) throws IOException {
        return toString(url, Charset.forName(charsetName));
    }

    public static int copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final long count = copyLarge(inputStream, outputStream);
        if (count > Integer.MAX_VALUE) {
            return EOF;
        }
        return (int) count;
    }

    public static long copy(final InputStream inputStream, final OutputStream outputStream, final int bufferSize) throws IOException {
        return copyLarge(inputStream, outputStream, byteArray(bufferSize));
    }

    public static void copy(final InputStream input, final Writer writer, final Charset inputCharset) throws IOException {
        final InputStreamReader reader = new InputStreamReader(input, inputCharset);
        copy(reader, writer);
    }

    public static void copy(final InputStream input, final Writer writer, final String inputCharsetName) throws IOException {
        copy(input, writer, Charset.forName(inputCharsetName));
    }

    public static long copy(final Reader reader, final Appendable output) throws IOException {
        return copy(reader, output, CharBuffer.allocate(DEFAULT_BUFFER_SIZE));
    }

    public static long copy(final Reader reader, final Appendable output, final CharBuffer buffer) throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = reader.read(buffer))) {
            buffer.flip();
            output.append(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static void copy(final Reader reader, final OutputStream output, final Charset outputCharset) throws IOException {
        final OutputStreamWriter writer = new OutputStreamWriter(output, outputCharset);
        copy(reader, writer);
        writer.flush();
    }

    public static void copy(final Reader reader, final OutputStream output, final String outputCharsetName)
            throws IOException {
        copy(reader, output, Charset.forName(outputCharsetName));
    }

    public static int copy(final Reader reader, final Writer writer) throws IOException {
        final long count = copyLarge(reader, writer);
        if (count > Integer.MAX_VALUE) {
            return EOF;
        }
        return (int) count;
    }

    public static long copy(final URL url, final File file) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(Objects.requireNonNull(file, "file"))) {
            return copy(url, outputStream);
        }
    }

    public static long copy(final URL url, final OutputStream outputStream) throws IOException {
        try (InputStream inputStream = Objects.requireNonNull(url, "url").openStream()) {
            return copyLarge(inputStream, outputStream);
        }
    }

    public static long copyLarge(final InputStream inputStream, final OutputStream outputStream)
            throws IOException {
        return copy(inputStream, outputStream, DEFAULT_BUFFER_SIZE);
    }

    public static long copyLarge(final InputStream inputStream, final OutputStream outputStream, final byte[] buffer) throws IOException {
        Objects.requireNonNull(inputStream, "inputStream");
        Objects.requireNonNull(outputStream, "outputStream");
        long count = 0;
        int n;
        while (EOF != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static long copyLarge(final InputStream input, final OutputStream output, final long inputOffset, final long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, getByteArray());
    }

    public static long copyLarge(final InputStream input, final OutputStream output, final long inputOffset, final long length, final byte[] buffer) throws IOException {
        if (inputOffset > 0) {
            skipFully(input, inputOffset);
        }
        if (length == 0) {
            return 0;
        }
        final int bufferLength = buffer.length;
        int bytesToRead = bufferLength;
        if (length > 0 && length < bufferLength) {
            bytesToRead = (int) length;
        }
        int read;
        long totalRead = 0;
        while (bytesToRead > 0 && EOF != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += read;
            if (length > 0) { // only adjust length if not reading to the end
                // Note the cast must work because buffer.length is an integer
                bytesToRead = (int) Math.min(length - totalRead, bufferLength);
            }
        }
        return totalRead;
    }

    public static long copyLarge(final Reader reader, final Writer writer) throws IOException {
        return copyLarge(reader, writer, getCharArray());
    }

    public static long copyLarge(final Reader reader, final Writer writer, final char[] buffer) throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = reader.read(buffer))) {
            writer.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static byte[] byteArray() {
        return byteArray(DEFAULT_BUFFER_SIZE);
    }

    public static byte[] byteArray(final int size) {
        return new byte[size];
    }

    static byte[] getByteArray() {
        return SKIP_BYTE_BUFFER.get();
    }

    private static char[] charArray() {
        return charArray(DEFAULT_BUFFER_SIZE);
    }

    private static char[] charArray(final int size) {
        return new char[size];
    }

    static char[] getCharArray() {
        return SKIP_CHAR_BUFFER.get();
    }

    public static void skipFully(final InputStream input, final long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        final long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static long skip(final InputStream input, final long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        long remain = toSkip;
        while (remain > 0) {
            final byte[] byteArray = getByteArray();
            final long n = input.read(byteArray, 0, (int) Math.min(remain, byteArray.length));
            if (n < 0) {
                break;
            }
            remain -= n;
        }
        return toSkip - remain;
    }

    public static byte[] toByteArray(final InputStream inputStream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int n;
        while (EOF != (n = inputStream.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    public static byte[] toByteArray(final InputStream input, final int size) throws IOException {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }
        if (size == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final byte[] data = org.apache.commons.io.IOUtils.byteArray(size);
        int offset = 0;
        int read;
        while (offset < size && (read = input.read(data, offset, size - offset)) != EOF) {
            offset += read;
        }
        if (offset != size) {
            throw new IOException("Unexpected read size, current: " + offset + ", expected: " + size);
        }
        return data;
    }

    public static byte[] toByteArray(final InputStream input, final long size) throws IOException {
        if (size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
        }
        return toByteArray(input, (int) size);
    }

    public static byte[] toByteArray(final Reader reader, final Charset charset) throws IOException {
        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            copy(reader, output, charset);
            return output.toByteArray();
        }
    }

    public static byte[] toByteArray(final Reader reader, final String charsetName) throws IOException {
        return toByteArray(reader, Charset.forName(charsetName));
    }

    public static byte[] toByteArray(final URI uri) throws IOException {
        return toByteArray(uri.toURL());
    }

    public static byte[] toByteArray(final URL url) throws IOException {
        final URLConnection conn = url.openConnection();
        try {
            return toByteArray(conn);
        } finally {
            close(conn);
        }
    }

    public static byte[] toByteArray(final URLConnection urlConn) throws IOException {
        try (InputStream inputStream = urlConn.getInputStream()) {
            return toByteArray(inputStream);
        }
    }

    public static char[] toCharArray(final InputStream inputStream, final Charset charset) throws IOException {
        final CharArrayWriter writer = new CharArrayWriter();
        copy(inputStream, writer, charset);
        return writer.toCharArray();
    }

    public static char[] toCharArray(final InputStream inputStream, final String charsetName) throws IOException {
        return toCharArray(inputStream, Charset.forName(charsetName));
    }

    public static char[] toCharArray(final Reader reader) throws IOException {
        final CharArrayWriter sw = new CharArrayWriter();
        copy(reader, sw);
        return sw.toCharArray();
    }

    public static void write(final byte[] data, final OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    public static void write(final byte[] data, final Writer writer, final Charset charset) throws IOException {
        if (data != null) {
            writer.write(new String(data, charset));
        }
    }

    public static void write(final byte[] data, final Writer writer, final String charsetName) throws IOException {
        write(data, writer, Charset.forName(charsetName));
    }

    public static void write(final char[] data, final OutputStream output, final Charset charset) throws IOException {
        if (data != null) {
            output.write(new String(data).getBytes(charset));
        }
    }

    public static void write(final char[] data, final OutputStream output, final String charsetName)
            throws IOException {
        write(data, output, Charset.forName(charsetName));
    }

    public static void write(final char[] data, final Writer writer) throws IOException {
        if (data != null) {
            writer.write(data);
        }
    }

    public static void write(final CharSequence data, final OutputStream output, final Charset charset)
            throws IOException {
        if (data != null) {
            write(data.toString(), output, charset);
        }
    }

    public static void write(final CharSequence data, final OutputStream output, final String charsetName)
            throws IOException {
        write(data, output, Charset.forName(charsetName));
    }

    public static void write(final CharSequence data, final Writer writer) throws IOException {
        if (data != null) {
            write(data.toString(), writer);
        }
    }

    public static void write(final String data, final OutputStream output, final Charset charset) throws IOException {
        if (data != null) {
            output.write(data.getBytes(charset));
        }
    }

    public static void write(final String data, final OutputStream output, final String charsetName)
            throws IOException {
        write(data, output, Charset.forName(charsetName));
    }

    public static void write(final String data, final Writer writer) throws IOException {
        if (data != null) {
            writer.write(data);
        }
    }

    public static InputStream toInputStream(final CharSequence input, final Charset charset) {
        return toInputStream(input.toString(), charset);
    }

    public static InputStream toInputStream(final CharSequence input, final String charsetName) throws IOException {
        return toInputStream(input, Charset.forName(charsetName));
    }

    public static InputStream toInputStream(final String input, final Charset charset) {
        return new ByteArrayInputStream(input.getBytes(charset));
    }

    public static InputStream toInputStream(final String input, final String charsetName) throws IOException {
        final byte[] bytes = input.getBytes(Charset.forName(charsetName));
        return new ByteArrayInputStream(bytes);
    }

    public static List<String> readLines(final InputStream input, final Charset charset) throws IOException {
        final InputStreamReader reader = new InputStreamReader(input, charset);
        return readLines(reader);
    }

    public static List<String> readLines(final InputStream input, final String charsetName) throws IOException {
        return readLines(input, Charset.forName(charsetName));
    }

    @SuppressWarnings("resource")
    public static List<String> readLines(final Reader reader) throws IOException {
        final BufferedReader bufReader = new BufferedReader(reader);
        final List<String> list = new ArrayList<>();
        String line;
        while ((line = bufReader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static LineIterator lineIterator(final InputStream input, final Charset charset) throws IOException {
        return new LineIterator(new InputStreamReader(input, charset));
    }

    public static LineIterator lineIterator(final InputStream input, final String charsetName) throws IOException {
        return lineIterator(input, Charset.forName(charsetName));
    }

    public static LineIterator lineIterator(final Reader reader) {
        return new LineIterator(reader);
    }

    public static void readFully(final InputStream input, final byte[] buffer) throws IOException {
        readFully(input, buffer, 0, buffer.length);
    }

    public static void readFully(final InputStream input, final byte[] buffer, final int offset, final int length)
            throws IOException {
        final int actual = read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static byte[] readFully(final InputStream input, final int length) throws IOException {
        final byte[] buffer = byteArray(length);
        readFully(input, buffer, 0, buffer.length);
        return buffer;
    }

    public static void readFully(final ReadableByteChannel input, final ByteBuffer buffer) throws IOException {
        final int expected = buffer.remaining();
        final int actual = read(input, buffer);
        if (actual != expected) {
            throw new EOFException("Length to read: " + expected + " actual: " + actual);
        }
    }

    public static void readFully(final Reader reader, final char[] buffer) throws IOException {
        readFully(reader, buffer, 0, buffer.length);
    }

    public static void readFully(final Reader reader, final char[] buffer, final int offset, final int length) throws IOException {
        final int actual = read(reader, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static int read(final InputStream input, final byte[] buffer, final int offset, final int length) throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        int remaining = length;
        while (remaining > 0) {
            final int location = length - remaining;
            final int count = input.read(buffer, offset + location, remaining);
            if (EOF == count) { // EOF
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    public static int read(final Reader reader, final char[] buffer, final int offset, final int length) throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        int remaining = length;
        while (remaining > 0) {
            final int location = length - remaining;
            final int count = reader.read(buffer, offset + location, remaining);
            if (EOF == count) { // EOF
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    public static int read(final ReadableByteChannel input, final ByteBuffer buffer) throws IOException {
        final int length = buffer.remaining();
        while (buffer.remaining() > 0) {
            final int count = input.read(buffer);
            if (EOF == count) { // EOF
                break;
            }
        }
        return length - buffer.remaining();
    }
}
