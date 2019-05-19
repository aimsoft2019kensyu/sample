package jp.co.aimsoft.common.constants;

public enum ProcessingResult {

	SUCCESS("success"),

	FAILURE("failure");

	private String value;

	public String getValue() {
		return value;
	}

	public boolean isSucceess() {
		return SUCCESS.getValue().equals(this.value);
	}

	private ProcessingResult(String value) {
		this.value = value;
	}
}
