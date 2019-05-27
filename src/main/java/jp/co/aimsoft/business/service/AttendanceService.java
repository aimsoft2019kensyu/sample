package jp.co.aimsoft.business.service;

import java.util.List;

import jp.co.aimsoft.business.model.TAttendance;

public interface AttendanceService {

	void register(TAttendance attendance);

	List<TAttendance> searchById(TAttendance attendance);

	void update(TAttendance attendance);

	void deleteOne(TAttendance attendance);
}
