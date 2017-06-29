package com.sas.pdg.mrr.filter;

import java.lang.reflect.Method;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.LoginRequiredException;

public class DWRSessionAjaxFilter implements AjaxFilter {
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
    	
    	/*
        if( WebContextFactory.get().getSession( false ) == null ) {
            //Throw an exception
            throw new LoginRequiredException( "Session过期，请重新刷新页面" );
        }
		*/
    	
        return chain.doFilter( obj, method, params );
    }
}