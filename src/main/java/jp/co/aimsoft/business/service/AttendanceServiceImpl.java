package jp.co.aimsoft.business.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import jp.co.aimsoft.business.model.TAttendance;
import jp.co.aimsoft.common.constants.MessageConstant;
import jp.co.aimsoft.common.exception.BusinessException;
import jp.co.aimsoft.integration.entity.AttendanceListCustomEntity;
import jp.co.aimsoft.integration.entity.AttendanceUpdateCustomEntity;
import jp.co.aimsoft.integration.entity.MUserEntity;
import jp.co.aimsoft.integration.entity.TAttendanceEntity;
import jp.co.aimsoft.integration.mapper.MUserMapper;
import jp.co.aimsoft.integration.mapper.TAttendanceMapper;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private TAttendanceMapper attendanceMapper;

	@Autowired
	private MUserMapper userMapper;

	@Override
	public void register(TAttendance attendance) {

		TAttendanceEntity attendanceEntity = null;

		try {
			attendanceEntity = createTAttendanceEntity(attendance);
		} catch (Exception e) {
			// 日付変換エラー時は画面にエラー文言を出す
			throw new BusinessException(MessageConstant.MSG_0004);
		}

		MUserEntity userEntity = new MUserEntity();
		userEntity.setId(attendance.getId());
		MUserEntity resultMUserEntity = userMapper.findOne(userEntity);

		if (resultMUserEntity == null) {
			// 入力された社員IDに紐づくユーザー情報が存在しない場合エラーとする。
			throw new BusinessException(MessageConstant.MSG_0005);
		}

		try {
			// 勤怠登録
			attendanceMapper.register(attendanceEntity);
		} catch (DuplicateKeyException e) {
			// 一意制約違反発生時エラーを捕捉しエラー文言を書き換える
			throw new BusinessException(MessageConstant.MSG_0001);
		}
	}

	@Override
	public List<TAttendance> searchById(TAttendance attendance) {

		TAttendanceEntity entity = new TAttendanceEntity();
		entity.setId(attendance.getId());

		List<AttendanceListCustomEntity> list = attendanceMapper.searchById(entity);

		List<TAttendance> attendanceList = new ArrayList<TAttendance>();

		// listの各要素に対して、TAttendanceを作成してコピーし、attendanceListへ追加する。
		list.forEach(attendanceEntity -> {
			attendanceList.add(createTAttendance(attendanceEntity));
		});

		return attendanceList;
	}

	@Override
	public void update(TAttendance attendance) {

		// 1. 更新対象ユーザーが存在するかチェックする
		MUserEntity userEntity = new MUserEntity() {
			{
				setId(attendance.getId());
			}
		};
		MUserEntity resultEntity = userMapper.findOne(userEntity);
		if (resultEntity == null) {
			// 更新対象ユーザーがいない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0005);
		}

		// 2. 更新対象が存在するかチェックする
		TAttendanceEntity targetAttendanceEntity = new TAttendanceEntity();
		targetAttendanceEntity.setId(attendance.getId());
		targetAttendanceEntity.setAttendanceFlg(attendance.getTargetAttendanceFlg());
		targetAttendanceEntity.setAttendanceDate(Date.valueOf(attendance.getTargetAttendanceDate()));

		TAttendanceEntity resultAttendanceEntity = attendanceMapper.findOne(targetAttendanceEntity);
		if (resultAttendanceEntity == null) {
			// 更新対象の勤怠が存在しない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0006);
		}

		// 3. 更新処理を行う。
		try {
			AttendanceUpdateCustomEntity attendanceCustomEntity = new AttendanceUpdateCustomEntity();
			attendanceCustomEntity.setId(attendance.getId());
			attendanceCustomEntity.setTargetAttendanceFlg(attendance.getTargetAttendanceFlg());
			attendanceCustomEntity.setTargetAttendanceDate(Date.valueOf(attendance.getTargetAttendanceDate()));
			attendanceCustomEntity.setAttendanceFlg(attendance.getAttendanceFlg());
			attendanceCustomEntity.setAttendanceDate(Date.valueOf(attendance.getAttendanceDate()));
			attendanceCustomEntity.setAttendanceTime(Time.valueOf(attendance.getAttendanceTime()));

			attendanceMapper.update(attendanceCustomEntity);
		} catch (DuplicateKeyException e) {
			throw new BusinessException(MessageConstant.MSG_0007);
		}
	}

	@Override
	public void deleteOne(TAttendance attendance) {

		// 削除対象ユーザーが存在するかチェックする
		MUserEntity userEntity = new MUserEntity();
		userEntity.setId(attendance.getId());
		MUserEntity resultUserEntity = userMapper.findOne(userEntity);
		if (resultUserEntity == null) {
			// 対象ユーザーがいない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0003);
		}

		// 削除対象レコードが存在するかチェック
		TAttendanceEntity attendanceEntity = createTAttendanceEntity(attendance);
		TAttendanceEntity resultAttendanceEntity = attendanceMapper.findOne(attendanceEntity);
		if (resultAttendanceEntity == null) {
			// 対象レコードが存在しない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0003);
		}

		attendanceMapper.deleteOne(attendanceEntity);

	}

	/**
	 * TAttendanceよりTAttendanceEntityへデータをコピーし返却する
	 * 
	 * @param attendance
	 *            TAttendance
	 * @return TAttendanceEntity
	 */
	private TAttendanceEntity createTAttendanceEntity(TAttendance attendance) {

		TAttendanceEntity entity = new TAttendanceEntity();
		entity.setId(attendance.getId());
		entity.setAttendanceFlg(attendance.getAttendanceFlg());
		entity.setAttendanceDate(Date.valueOf(attendance.getAttendanceDate()));
		// 勤怠削除の際、attendanceTimeがnullのケースが存在するため必要な時だけ移す
		if (attendance.getAttendanceTime() != null) {
			entity.setAttendanceTime(Time.valueOf(attendance.getAttendanceTime()));
		}

		return entity;
	}

	/**
	 * AttendanceListCustomEntityよりTAttendanceへデータをコピーし返却する
	 *
	 * @param AttendanceListCustomEntity
	 * @return TAttendance
	 */
	private TAttendance createTAttendance(AttendanceListCustomEntity entity) {

		TAttendance attendance = new TAttendance();
		attendance.setId(entity.getId());
		attendance.setName(entity.getName());
		attendance.setAttendanceFlg(entity.getAttendanceFlg());
		attendance.setAttendanceDate(entity.getAttendanceDate().toString());
		attendance.setAttendanceTime(entity.getAttendanceTime().toString());

		return attendance;
	}

}
