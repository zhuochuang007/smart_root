package com.zhuochuang.it.smart.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhuochuang.it.smart.constant.SystemConstant;

/**
 * 界面资源处理工具 <功能详细描述>
 * 
 * @author ouxin
 * @version [版本号, 2016年4月2日]
 */
public class WebResourcesUtil {
	private static Logger log = LoggerFactory.getLogger(WebResourcesUtil.class);

	/**
	 * 处理请求资源 <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static boolean process(HttpServletRequest request, HttpServletResponse response) {
		// 上下文根路径
		String contextPath = request.getContextPath();
		// 请求uri地址
		String uri = request.getRequestURI();
		if (StringUtil.isEmpty(uri)) {
			return false;
		}
		int suffixIndex = uri.lastIndexOf(".");
		if (suffixIndex == -1) {
			return false;
		}
		// 请求后缀
		String suffix = uri.substring(suffixIndex).replace(".", "");
		// 获取classpath
		int start = 0;
		int end = uri.length();
		if (!StringUtil.isEmpty(contextPath)) {
			start = contextPath.length();
		}
		// 请求路径
		String requestPath = uri.substring(start, end);
		// 请求路径为空或者结尾字符为斜杠
		if (StringUtil.isEmpty(requestPath) || requestPath.endsWith("/")) {
			return false;
		}
		// 资源类路径
		String classpath = new StringBuilder(SystemConstant.WEB_PREFIX).append(requestPath).toString();
		// 在classpath中获取资源
		URL url = WebResourcesUtil.class.getClassLoader().getResource(classpath);
		if (null == url) {
			return false;
		}
		// 获取文件路径
		String filePath = url.getFile();
		if (StringUtil.isEmpty(filePath)) {
			return false;
		}
		if (filePath.indexOf(SystemConstant.FILE_FLAG) == 0) {
			filePath = filePath.substring(5);
		}
		if (StringUtil.isEmpty(filePath)) {
			return false;
		}
		int idx = filePath.indexOf(SystemConstant.IN_THE_JAR_FLAG);
		if (idx != -1) {
			filePath = filePath.substring(0, idx);
		}
		File file = new File(filePath);
		if (null == file || !file.exists() || !file.isFile()) {
			return false;
		}
		// 处理文件流
		InputStream resource = null;
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(ContentTypeUtil.getContentType(suffix));
			resource = url.openStream();
			StreamUtil.transferStream(resource, response.getOutputStream());
		} catch (IOException e) {
			log.info("处理文件流错误", e);
			return false;
		} finally {
			StreamUtil.closeStream(resource);
		}
		return true;
	}
}
