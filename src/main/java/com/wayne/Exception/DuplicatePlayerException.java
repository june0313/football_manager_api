package com.wayne.exception;

/**
 * @author wayne
 * @version 1.0
 */
public class DuplicatePlayerException extends RuntimeException {

	private String duplicatedEmail;

	public DuplicatePlayerException(String email) {
		this.duplicatedEmail = email;
	}

	@Override
	public String getMessage() {
		return "이미 존재하는 사용자입니다. 사용자 email : " + duplicatedEmail + "\n";
	}
}
