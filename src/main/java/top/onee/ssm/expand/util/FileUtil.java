package top.onee.ssm.expand.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件工具类
 *
 * Created by VOREVER on 2017/4/28.
 */
public class FileUtil {

    /**
     * 下载文件
     *
     * @param path
     * @param response
     * @return
     */
    public static HttpServletResponse downloadFile(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            String filename = file.getName();
            /*以流的形式下载文件*/
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            /*清空response*/
            response.reset();
            /*设置response的Header*/
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
