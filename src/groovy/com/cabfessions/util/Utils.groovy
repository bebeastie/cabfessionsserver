package com.cabfessions.util

import javax.crypto.KeyGenerator


public class Utils {
	public static String generateUserKey() {
		UUID.randomUUID().toString();	
	}	
}