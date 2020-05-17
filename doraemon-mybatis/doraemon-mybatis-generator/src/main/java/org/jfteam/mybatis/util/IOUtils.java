package org.jfteam.mybatis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/7/14 21:58
 */
public final class IOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    public static void close(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            LOGGER.error("close io failure", ioe);
        }
    }

    public static String copyToString(InputStream in, Charset charset) throws IOException {
        if (in == null) {
            return "";
        } else {
            StringBuilder out = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, charset);
            char[] buffer = new char[4096];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                out.append(buffer, 0, bytesRead);
            }
            return out.toString();
        }
    }
}
