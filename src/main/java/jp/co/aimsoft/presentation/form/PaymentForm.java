package jp.co.aimsoft.presentation.form;

import java.util.Arrays;
import java.util.List;

public class PaymentForm {

	private Long id;

	private String paymentYear;

	private String paymentMonth;

	private Long paymentAmount;

	// 以下、更新用
	private String targetPaymentYear;

	private String targetPaymentMonth;

	private List<String> yearList;

	private List<String> monthList;

	private List<String> dayList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentYear() {
		return paymentYear;
	}

	public void setPaymentYear(String paymentYear) {
		this.paymentYear = paymentYear;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getTargetPaymentYear() {
		return targetPaymentYear;
	}

	public void setTargetPaymentYear(String targetPaymentYear) {
		this.targetPaymentYear = targetPaymentYear;
	}

	public String getTargetPaymentMonth() {
		return targetPaymentMonth;
	}

	public void setTargetPaymentMonth(String targetPaymentMonth) {
		this.targetPaymentMonth = targetPaymentMonth;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public List<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}

	public List<String> getDayList() {
		return dayList;
	}

	public void setDayList(List<String> dayList) {
		this.dayList = dayList;
	}

	public PaymentForm() {

		// とりあえず固定で生成しておく
		List<String> years = Arrays.asList("2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
				"2028", "2029", "2030");
		this.yearList = years;

		List<String> months = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		this.monthList = months;

		List<String> days = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
		this.dayList = days;
	}

}
