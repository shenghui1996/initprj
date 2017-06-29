package com.sas.pdg.mrr.controller;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sas.pdg.mrr.dto.UserDTO;
import com.sas.pdg.mrr.service.IdentityService; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShareController {
	
	@RequestMapping("/*.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
    	String path = request.getServletPath();

    	if(path.startsWith("/ajax")){
    		request.setAttribute("gbin1theme", "gbajax");
    	}else{
    		request.setAttribute("gbin1theme", "maintpl");
    	}
        	
        return new ModelAndView();
    }  
    
    @RequestMapping("/user/{uid}.htm")
    public ModelAndView gbmessageHandleController(HttpServletRequest request, HttpServletResponse response, @PathVariable String uid) throws UnsupportedEncodingException {
    	request.setAttribute("gbin1.uid", uid);
    	request.setAttribute("gbin1theme", "maintpl");
    	return new ModelAndView("gbmessage");
    }
}
