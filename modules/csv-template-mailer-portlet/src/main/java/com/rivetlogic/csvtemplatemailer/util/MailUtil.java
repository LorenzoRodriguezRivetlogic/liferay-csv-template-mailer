package com.rivetlogic.csvtemplatemailer.util;

import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;

public class MailUtil {
	public static void sendEmails(String senderEmail, String body, String subject, List<Map<String, String>> data) {
        InternetAddress from = null;
		try {
			from = new InternetAddress(senderEmail);
		} catch (AddressException e1) {
			e1.printStackTrace();
		}
        
        for (Map<String, String> dataRow : data) {
        	try {
        		System.out.println("Sending email...");
	        	MailMessage message = generateMailMessage(from, dataRow, body, subject);
	        	MailEngine.send(message.getFrom(), message.getTo(), message.getCC(), message.getBCC(), message.getSubject(), message.getBody(), true, message.getReplyTo(), message.getMessageId(), message.getInReplyTo());
        	}catch(MailEngineException e){
        		System.out.println("MailEngineException");
        	} catch (AddressException e) {
        		System.out.println("AddressException");
			}
		}
    }
    
	public static MailMessage generateMailMessage(InternetAddress from, Map<String, String> dataRow, String body, String subject) 
    		throws AddressException {
		
    	InternetAddress to = new InternetAddress(dataRow.get(WebKeys.EMAIL_TO_SEND));
    	String tmpBody = body;
    	
    	for (Map.Entry<String, String> entry : dataRow.entrySet()) {
    	    String key = entry.getKey();
    	    String value = entry.getValue();
    	    
    	    if (key.equals(WebKeys.EMAIL_TO_SEND)) {
    	    	continue;
    	    }
    	    
    	    tmpBody = tmpBody.replace(key, value);
    	}
    	
    	tmpBody = Utils.removeUnwantedColumns(tmpBody);
    	return new MailMessage(from, to, subject, tmpBody, true);
	}
}
