package com.sas.pdg.mrr.util;

import java.net.URL;

import org.apache.commons.mail.*;

public class MailUtil{

	public static void sendHTMLMail(String host,String port,String user, String pwd, String tolist, String fullname, String from){
		try{
		  // Create the email message
		  HtmlEmail email = new HtmlEmail();
		  email.setDebug(true);
		  email.setHostName(host);
		  email.setSSL(Boolean.TRUE); 
		  //email.setSmtpPort(587);
		  email.setSslSmtpPort(port);
		  email.setAuthentication(user, pwd);
		  email.addTo(tolist, fullname);
		  email.setFrom(from, "System Email");
		  email.setSubject("Test email with inline image");

		  // set the html message
		  email.setHtmlMsg("<html>The apache logo - <img src=\"test\"></html>");
		
		  // set the alternative message
		  email.setTextMsg("Your email client does not support HTML messages");
		
		  // send the email
		  email.send();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		MailUtil.sendHTMLMail("smtp.gmail.com","465","spiritseekers@gmail.com","????","spiritseekers@gmail.com","terry li","peoplen2et@gmail.com");
	}
}
