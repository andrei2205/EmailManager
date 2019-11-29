package com.ib.mobile.stdconnector.shared;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailManager {

	public void sendEmail(String toEmail, String subject, String body) {
		boolean onErrorSendMail = true;
		boolean useSSLAuthentication = true;
		

		if (onErrorSendMail == true && useSSLAuthentication == true) {
			try {
				// Send Mail in Java using SMTP with SSL authentication
				System.out.println("Send Mail in Java using SMTP with authentication");
				Properties props = System.getProperties();
				props.put("mail.smtp.host", "mail.zucchetti.it");
				props.put("mail.smtp.user", "andrei.cazan@zucchettiromania.com");
				props.put("mail.smtp.port", "25");
				props.put("mail.smtp.auth", "true");
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("user", "password");
					}
				});
				MimeMessage msg = new MimeMessage(session);
				// set message headers
				String[] recipientList = toEmail.toString().split(";");
				InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
				int counter = 0;
				for (String recipient : recipientList) {
					recipientAddress[counter] = new InternetAddress(recipient.trim());
					counter++;
				}

				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encoding", "8bit");

				msg.setFrom(new InternetAddress("andrei.cazan@zucchettiromania.com", "NoReply-JD"));

				msg.setReplyTo(InternetAddress.parse("user", false));

				msg.setSubject(subject, "UTF-8");

				msg.setText(body, "UTF-8");

				msg.setSentDate(new Date());

				msg.setRecipients(Message.RecipientType.TO, recipientAddress);
				System.out.println("Message is ready");
				Transport.send(msg);
				System.out.println("EMail Sent Successfully!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (onErrorSendMail == true && useSSLAuthentication == false) {
			
				try {
					// Send Mail in Java using SMTP without authentication

					System.out.println("Send Mail in Java using SMTP without authentication");

					String smtpHostServer = "mail.zucchetti.it";
					String emailID = "andrei.cazan@zucchettiromania.com";

					Properties props = System.getProperties();

					props.put("mail.smtp.host", smtpHostServer);

					Session session = Session.getInstance(props, null);
					MimeMessage msg = new MimeMessage(session);
					// set message headers
					String[] recipientList = toEmail.toString().split(";");
					InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
					int counter = 0;
					for (String recipient : recipientList) {
						recipientAddress[counter] = new InternetAddress(recipient.trim());
						counter++;
					}

					msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
					msg.addHeader("format", "flowed");
					msg.addHeader("Content-Transfer-Encoding", "8bit");

					msg.setFrom(new InternetAddress("andrei.cazan@zucchettiromania.com", "NoReply-JD"));

					msg.setReplyTo(InternetAddress.parse("user", false));

					msg.setSubject(subject, "UTF-8");

					msg.setText(body, "UTF-8");

					msg.setSentDate(new Date());

					msg.setRecipients(Message.RecipientType.TO, recipientAddress);
					System.out.println("Message is ready");
					Transport.send(msg);
					System.out.println("EMail Sent Successfully!!");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}

