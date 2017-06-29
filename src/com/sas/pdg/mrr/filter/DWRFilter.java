package com.sas.pdg.mrr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sas.pdg.mrr.exception.BusinessException;



public class DWRFilter implements Filter
{
  public void destroy()
  {
    // TODO Auto-generated method stub

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
  {
    if (request instanceof HttpServletRequest && response instanceof HttpServletResponse)
    {
      final HttpServletRequest httpRequest = (HttpServletRequest) request;
      final HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpRequest.setCharacterEncoding("UTF-8");
      HttpSession httpSession = httpRequest.getSession(false);

      if (httpSession == null)
      {
        throw new BusinessException("com.gbin1.dwr.session.null");
      }else{
    	
      }
    }
    filterChain.doFilter(request, response);
  }

  public void init(FilterConfig arg0) throws ServletException
  {
    // TODO Auto-generated method stub

  }
}
