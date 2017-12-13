import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: 2/26/13
 * Time: 9:48 AM
 */
public class FileServlet extends HttpServlet {

    public static Logger fileServerLogger = Logger.getLogger("fileServerLogger");

    private Configuration configuration =  new Configuration();

    String HADOOP_HOME = "/usr/local/hadoop-2.8.1";
//    String PROP_NAME = "fs.default.name";
    public FileServlet() {
        configuration.addResource(new Path(HADOOP_HOME+"/etc/hadoop/core-site.xml"));
        configuration.addResource(new Path(HADOOP_HOME+"/etc/hadoop/hdfs-site.xml"));
        configuration.set("fs.hdfs.impl",
                org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
        );
        configuration.set("fs.file.impl",
                org.apache.hadoop.fs.LocalFileSystem.class.getName()
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        byte[] data = org.apache.commons.io.IOUtils.toByteArray(inputStream);
        String fileType = request.getHeader("fileType");
        System.out.println(request.getHeader("Filename"));
        System.out.println(request.getHeader(""));
        String fileName = put(data, fileType);
        fileServerLogger.info(fileName + " upload");
        response.getWriter().write(fileName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        if (fileName != null && !fileName.equals("")){
            byte[] fileBytes = get(request.getParameter("fileName"));
            response.setHeader("Content-Type",  getFileExtension(fileName));
            response.getOutputStream().write(fileBytes);
            fileServerLogger.info(fileName + " download");
        }
    }

    private String getFileExtension(String fileName) {
        int extensionIndex = fileName.indexOf(".");
        if (extensionIndex == -1)
            return "*/*";
        String fileExtension = fileName.substring(extensionIndex + 1);
        if(fileExtension.equals("gif") || fileExtension.equals("png") || fileExtension.equals("bmp") || fileExtension.equals("jpeg"))
            return "image/"+fileExtension;
        if (fileExtension.equals("jpg"))
            return "image/jpeg";
        if (fileExtension.equals("svg"))
            return "image/webp";
        if (fileExtension.equals("pdf"))
            return "application/pdf";
        if (fileExtension.equals("mp3"))
            return "audio/mpeg";
        if (fileExtension.equals("midi") || fileExtension.equals("ogg") || fileExtension.equals("wav") || fileExtension.equals("ogg"))
            return "audio/"+fileExtension;
        if (fileExtension.equals("mp4"))
            return "video/mp4";
        return "*/*";
    }


    public String put(byte[] data, String type) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + type;
        FileSystem fs = FileSystem.get(configuration);
        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/test2/" + fileName));
        fsDataOutputStream.write(data);
        fs.close();
        return fileName;
    }

    public byte[] get(String fileName) throws IOException {
        FileSystem fs = FileSystem.get(configuration);
        FSDataInputStream inputStream = fs.open(new Path("/test2/" + fileName));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copyBytes(inputStream, byteArrayOutputStream, 1024);
        IOUtils.closeStream(inputStream);
        fs.close();
        return byteArrayOutputStream.toByteArray();
    }


}
