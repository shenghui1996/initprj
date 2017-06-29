package com.sas.pdg.mrr.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sas.pdg.mrr.dto.UserDTO;

public class SpringMailUtil {

	private VelocityEngine velocityEngine;
	private String confirmMailTitle;
	private String resetMailTitle;

	public void sendSimpleMail(JavaMailSender sender) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf8");
		helper.setFrom("master@gmail.com");
		helper.setTo("people2net@gmail.com");
		helper.setSubject("title");
		String htmlText = "<html>test mail sending</html>";
		helper.setText(htmlText, true);
		sender.send(msg);
		System.out.println("成功发送html邮件！");
	}
	
	//简单的邮件发送方法
	 public void sendConfirmationEmail(JavaMailSender sender, final UserDTO dto) {
	     MimeMessagePreparator preparator = new MimeMessagePreparator() {
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	           MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false,"utf8");   
	           message.setTo(dto.getMail());
	           message.setFrom("startup@gearpi.com"); // could be parameterized...
	           Map model = new HashMap();
	           model.put("userdto", dto);
	           String text = VelocityEngineUtils.mergeTemplateIntoString(
	              velocityEngine, "com/sas/pdg/mrr/util/regconfirm.vm", "UTF-8", model);
	           message.setSubject(confirmMailTitle);    
	           message.setText(text, true);
	        }
	     };
	     sender.send(preparator);
	     System.out.println("#################成功发送html邮件！");
	 }





	public void sendResetPasswordEmail(JavaMailSender sender, final UserDTO dto) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						false, "utf8");
				message.setTo(dto.getMail());
				message.setFrom("noreply@gbtags.com"); // could be
														// parameterized...
				Map model = new HashMap();
				model.put("dto", dto);
				String text = VelocityEngineUtils
						.mergeTemplateIntoString(velocityEngine,
								"com/sas/pdg/mrr/util/resetpassword.vm",
								"UTF-8", model);
				message.setSubject(resetMailTitle);
				message.setText(text, true);
			}
		};
		sender.send(preparator);
	}
	

	/**
	 * 拼接发送邮件的json串--
	 * 
	 * @param userDTO
	 * @return
	 */
	private static String convertToString(Map<String, String> map) {
		String jsonstr_ = " {\"to\": [\"" + map.get("mail") + "\"],"
				+ "\"sub\":{\"%fullname%\": [\"" + map.get("name")
				+ "\"],\"%vericode%\":[\"" + map.get("vericode")
				+ "\"],\"%mail%\":[\"" + map.get("mail") + "\"] }" + "    }";
		return jsonstr_.toString();

	}

	/*
	 * Spring IOC methods
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setConfirmMailTitle(String confirmMailTitle) {
		this.confirmMailTitle = confirmMailTitle;
	}

	public void setResetMailTitle(String resetMailTitle) {
		this.resetMailTitle = resetMailTitle;
	}

	/*
	 * main method for testing
	 */
	 public static void main(String[] args) {   
		 // TODO Auto-generated method stub   
		 ApplicationContext ctx = 
	         new ClassPathXmlApplicationContext("systemdispatcher-service.xml");

		 JavaMailSender sender=(JavaMailSender) ctx.getBean("mailsender");
		 try{
		  SpringMailUtil ms=new SpringMailUtil();   
		  ms.sendSimpleMail(sender);
		 }catch(Exception e){
		  e.printStackTrace();
		 }
	  }
	 
}
