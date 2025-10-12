package com.in28mintues.restful_web_service.helloworld.userdao;

public class UserNotFoundException  extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}

}
