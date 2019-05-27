package jp.co.aimsoft.presentation.form;

import java.util.Arrays;
import java.util.List;

public class AttendanceForm {

	private Long id;

	private String attendanceFlg;

	private String attendanceYear;

	private String attendanceMonth;

	private String attendanceDay;

	private String attendanceTime;

	// 以下、更新用
	private String targetAttendanceFlg;

	private String targetAttendanceYear;

	private String targetAttendanceMonth;

	private String targetAttendanceDay;

	private List<String> yearList;

	private List<String> monthList;

	private List<String> dayList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttendanceFlg() {
		return attendanceFlg;
	}

	public void setAttendanceFlg(String attendanceFlg) {
		this.attendanceFlg = attendanceFlg;
	}

	public String getAttendanceYear() {
		return attendanceYear;
	}

	public void setAttendanceYear(String attendanceYear) {
		this.attendanceYear = attendanceYear;
	}

	public String getAttendanceMonth() {
		return attendanceMonth;
	}

	public void setAttendanceMonth(String attendanceMonth) {
		this.attendanceMonth = attendanceMonth;
	}

	public String getAttendanceDay() {
		return attendanceDay;
	}

	public void setAttendanceDay(String attendanceDay) {
		this.attendanceDay = attendanceDay;
	}

	public String getAttendanceTime() {
		return attendanceTime;
	}

	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}

	public String getTargetAttendanceFlg() {
		return targetAttendanceFlg;
	}

	public void setTargetAttendanceFlg(String targetAttendanceFlg) {
		this.targetAttendanceFlg = targetAttendanceFlg;
	}

	public String getTargetAttendanceYear() {
		return targetAttendanceYear;
	}

	public void setTargetAttendanceYear(String targetAttendanceYear) {
		this.targetAttendanceYear = targetAttendanceYear;
	}

	public String getTargetAttendanceMonth() {
		return targetAttendanceMonth;
	}

	public void setTargetAttendanceMonth(String targetAttendanceMonth) {
		this.targetAttendanceMonth = targetAttendanceMonth;
	}

	public String getTargetAttendanceDay() {
		return targetAttendanceDay;
	}

	public void setTargetAttendanceDay(String targetAttendanceDay) {
		this.targetAttendanceDay = targetAttendanceDay;
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

	public AttendanceForm() {

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
