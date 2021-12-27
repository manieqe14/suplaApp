package com.maniek.exceptions;

import java.util.regex.Pattern;

public class Validations {
	
	public static boolean checkEmail(String email) {
		
		Pattern pattern = Pattern.compile(".+@.+\\.pl");
		return pattern.matcher(email).matches();
	}
	
	public static boolean checkIPAddress(String IPaddress) {
		
		Pattern pattern = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-4])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-4])$");
		return pattern.matcher(IPaddress).matches();
	}

}
