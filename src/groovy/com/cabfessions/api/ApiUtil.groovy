package com.cabfessions.api

import java.text.SimpleDateFormat;
import java.util.HashMap;
import com.cabfessions.User;

class ApiUtil {
	public static  SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")
	private static final double DEFAULT_GEO_RANGE = 0.007
	protected static HEADER_ERROR = "error"
	protected static HEADER_OK = "ok"
	
	public static String generateUserHashKey() {
		UUID.randomUUID().toString();	
	}	
	
	private static User verifyUser(userHashKey) {
		userHashKey ? User.findByHashKey(userHashKey) : null
	}
	
	protected static HashMap getErrorMap(String error) {
		def errors = [error]
		[errors: errors]
	}
	
	protected static HashMap getStatusOk() {
		[status: HEADER_OK]
	}
	
	protected static HashMap getStatusError(def code) {
		def map = [:]
		map.status = HEADER_ERROR
		if (code)
			map.code = code
		return map
	}	
	
	/**
	 * Wraps response with header and body.
	 * 
	 * @param contents
	 * @param error
	 * @return
	 */
	protected static def wrapResponse(contents, isSuccess, errorCode) {
		def output = [:]
		isSuccess ? output.put("header", getStatusOk()) : output.put("header", getStatusError(errorCode))
		output.put("body", contents)
		return output 
	}
	
	protected static def wrapResponse(contents, isSuccess) {
		wrapResponse(contents, isSuccess, null)
	}
	
	protected static def wrapError(error) {
		wrapResponse(getErrorMap(error), false, null)
	}
	
	protected static def wrapError(error, errorCode) {
		wrapResponse(getErrorMap(error), false, errorCode)
	}

	protected static HashMap wrapUserKeyError() {
		wrapResponse(getErrorMap("user_key is invalid or not supplied."),false)
	}
}
