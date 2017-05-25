package com.rivetlogic.csvtemplatemailer.task;

import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.rivetlogic.csvtemplatemailer.util.FileUtil;
import com.rivetlogic.csvtemplatemailer.util.MailUtil;

public class SendEmailTask implements Runnable {

	private Thread thread;
	
	private int sent = 0; 
	private int notSent = 0; 
	
	private List<Map<String, String>> data;
	private String senderEmail;
	private String content;
	private String emailSubject;
	private String fileId;
	
	public SendEmailTask() {
		thread = new Thread(this);
	}
	
	public void start(List<Map<String, String>> data, String senderEmail, String content, String emailSubject, String fileId){
		thread.start();
		this.data = data;
		this.senderEmail = senderEmail;
		this.content = content;
		this.emailSubject = emailSubject;
		this.fileId = fileId;
	}
	
	@Override
	public void run() {
		for (Map<String, String> dataRow : data) {
	    	MailMessage message;
			try {
				InternetAddress from  = new InternetAddress(senderEmail);
				message = MailUtil.generateMailMessage(from, dataRow, content, emailSubject);
				MailEngine.send(message.getFrom(), message.getTo(), message.getCC(), message.getBCC(), message.getSubject(), message.getBody(), true, message.getReplyTo(), message.getMessageId(), message.getInReplyTo());
				sent++;
		    } catch (Exception e1) {
		    	notSent++;
				System.out.println("Exception");
			}
	    }
	}

	public Thread.State getState() {
        return thread.getState();
    }
	
	public void clean() {
		sent = 0;
		notSent = 0;
		thread = new Thread(this);
	}
	public int getSent() {
		return sent;
	}

	public void setSent(int sent) {
		this.sent = sent;
	}

	public int getNotSent() {
		return notSent;
	}

	public void setNotSent(int notSent) {
		this.notSent = notSent;
	}

}
