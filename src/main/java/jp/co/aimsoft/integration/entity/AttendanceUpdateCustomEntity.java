package jp.co.aimsoft.integration.entity;

import java.sql.Date;
import java.sql.Time;

public class AttendanceUpdateCustomEntity {

	private Long id;

	private String name;

	private String attendanceFlg;

	private Date attendanceDate;

	private Time attendanceTime;

	private String targetAttendanceFlg;

	private Date targetAttendanceDate;

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

	public String getAttendanceFlg() {
		return attendanceFlg;
	}

	public void setAttendanceFlg(String attendanceFlg) {
		this.attendanceFlg = attendanceFlg;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Time getAttendanceTime() {
		return attendanceTime;
	}

	public void setAttendanceTime(Time attendanceTime) {
		this.attendanceTime = attendanceTime;
	}

	public String getTargetAttendanceFlg() {
		return targetAttendanceFlg;
	}

	public void setTargetAttendanceFlg(String targetAttendanceFlg) {
		this.targetAttendanceFlg = targetAttendanceFlg;
	}

	public Date getTargetAttendanceDate() {
		return targetAttendanceDate;
	}

	public void setTargetAttendanceDate(Date targetAttendanceDate) {
		this.targetAttendanceDate = targetAttendanceDate;
	}

}
