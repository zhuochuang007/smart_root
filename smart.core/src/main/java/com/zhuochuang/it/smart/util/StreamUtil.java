package com.zhuochuang.it.smart.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** 
 * IO处理工具
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年4月2日] 
 */
public class StreamUtil
{
    /** 
     * 流对接处理
     * <功能详细描述>
     * @param in
     * @param out
     * @throws IOException 
     */
    public static void transferStream(InputStream in, OutputStream out)
        throws IOException
    {
        if (null == in || null == out)
        {
            return;
        }
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, len);
        }
    }
    
    /** 
     * 关闭流 
     * <功能详细描述>
     * @param stream 
     */
    public static void closeStream(Closeable stream)
    {
        if (null != stream)
        {
            try
            {
                stream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
