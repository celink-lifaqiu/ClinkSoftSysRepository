package com.wifiswitch.service.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;



public class LvAuthenticator extends Authenticator{
	String userName = null;
	String password = null;

	public LvAuthenticator() {
	}

	public LvAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
