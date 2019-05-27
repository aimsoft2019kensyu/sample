package jp.co.aimsoft.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.aimsoft.integration.entity.AttendanceListCustomEntity;
import jp.co.aimsoft.integration.entity.AttendanceUpdateCustomEntity;
import jp.co.aimsoft.integration.entity.TAttendanceEntity;

@Mapper
public interface TAttendanceMapper {

	int register(TAttendanceEntity entity);

	TAttendanceEntity findOne(TAttendanceEntity entity);

	List<AttendanceListCustomEntity> searchById(TAttendanceEntity entity);

	int update(AttendanceUpdateCustomEntity entity);

	int deleteOne(TAttendanceEntity entity);
}
