package jp.co.aimsoft.integration.entity;

import java.sql.Date;
import java.sql.Time;

public class AttendanceListCustomEntity {

	private Long id;

	private String name;

	private String attendanceFlg;

	private Date attendanceDate;

	private Time attendanceTime;

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

}
