package ir.ord.application.filters;

import ir.ord.application.accessories.Helper;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created by vahid on 7/16/17.
 */
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public CustomHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            IOUtils.copy(request.getInputStream(), outputStream);
        } catch (IOException e) {
            Helper.appLogger.error("CustomHttpServletRequestWrapper", e);
        }
    }
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray())));
    }

    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new ServletInputStream() {

            public int readLine(byte[] b, int off, int len) throws IOException {
                return inputStream.read(b, off, len);
            }

            public boolean isFinished() {
                return inputStream.available() > 0;
            }

            public boolean isReady() {
                return true;
            }

            public void setReadListener(ReadListener arg0) {
                // TODO Auto-generated method stub
            }

            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    public void setBody(byte[] body) {
        outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(body);
        } catch (IOException e) {
            Helper.appLogger.error("setBody", e);
        }
    }

    public byte[] getBody() {
        return outputStream.toByteArray();
    }
}
