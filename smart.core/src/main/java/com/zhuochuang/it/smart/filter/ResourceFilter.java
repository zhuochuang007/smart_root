package com.zhuochuang.it.smart.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuochuang.it.smart.util.WebResourcesUtil;

/** 
 * 静态资源过滤器
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年4月2日] 
 */
public class ResourceFilter implements Filter
{
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //处理网页资源，处理成功直接返回，失败继续过滤器链
        boolean flag = WebResourcesUtil.process(request, response);
        if(!flag){
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy()
    {
    }
    
}
