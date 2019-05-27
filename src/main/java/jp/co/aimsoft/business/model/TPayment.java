package jp.co.aimsoft.business.model;

public class TPayment {

	private Long id;

	private String name;

	private String paymentDate;

	private Long paymentAmount;

	private String targetPaymentDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getTargetPaymentDate() {
		return targetPaymentDate;
	}

	public void setTargetPaymentDate(String targetPaymentDate) {
		this.targetPaymentDate = targetPaymentDate;
	}

}
