package jp.co.aimsoft.integration.entity;

import java.sql.Date;

public class TPaymentUpdateCustomEntity {

	private Long id;

	private Date paymentDate;

	private Long paymentAmount;

	private Date targetPaymentDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getTargetPaymentDate() {
		return targetPaymentDate;
	}

	public void setTargetPaymentDate(Date targetPaymentDate) {
		this.targetPaymentDate = targetPaymentDate;
	}

}
