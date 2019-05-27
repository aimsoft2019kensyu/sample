package jp.co.aimsoft.business.model;

public class TAttendance {

	private Long id;

	private String name;

	private String attendanceFlg;

	private String attendanceDate;

	private String attendanceTime;

	private String targetAttendanceFlg;

	private String targetAttendanceDate;

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

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
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

	public String getTargetAttendanceDate() {
		return targetAttendanceDate;
	}

	public void setTargetAttendanceDate(String targetAttendanceDate) {
		this.targetAttendanceDate = targetAttendanceDate;
	}

}
