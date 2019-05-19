package jp.co.aimsoft.common.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4477015653808977260L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessException(String message) {

		this.message = message;
	}
}
