package org.doraemon.framework.utility.input;

import org.doraemon.framework.utility.io.IOUtils;

import java.io.InputStream;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-11 17:22
 */
public class ClosedInputStream extends InputStream {

    public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();

    @Override
    public int read() {
        return IOUtils.EOF;
    }

}
