package jp.co.aimsoft.business.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import jp.co.aimsoft.business.model.TPayment;
import jp.co.aimsoft.common.constants.MessageConstant;
import jp.co.aimsoft.common.exception.BusinessException;
import jp.co.aimsoft.integration.entity.MUserEntity;
import jp.co.aimsoft.integration.entity.TPaymentEntity;
import jp.co.aimsoft.integration.entity.TPaymentListCustomEntity;
import jp.co.aimsoft.integration.entity.TPaymentUpdateCustomEntity;
import jp.co.aimsoft.integration.mapper.MUserMapper;
import jp.co.aimsoft.integration.mapper.TPaymentMapper;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private TPaymentMapper paymentMapper;

	@Autowired
	private MUserMapper userMapper;

	@Override
	public void register(TPayment payment) {

		TPaymentEntity paymentEntity = null;

		try {
			paymentEntity = createTPaymentEntity(payment);
		} catch (Exception e) {
			// 日付変換エラー時は画面にエラー文言を出す
			throw new BusinessException(MessageConstant.MSG_0004);
		}

		MUserEntity userEntity = new MUserEntity();
		userEntity.setId(payment.getId());
		MUserEntity resultMUserEntity = userMapper.findOne(userEntity);

		if (resultMUserEntity == null) {
			// 入力された社員IDに紐づくユーザー情報が存在しない場合エラーとする。
			throw new BusinessException(MessageConstant.MSG_0005);
		}

		try {
			// 勤怠登録
			paymentMapper.register(paymentEntity);
		} catch (DuplicateKeyException e) {
			// 一意制約違反発生時エラーを捕捉しエラー文言を書き換える
			throw new BusinessException(MessageConstant.MSG_0001);
		}
	}

	@Override
	public List<TPayment> searchById(TPayment payment) {

		TPaymentEntity entity = new TPaymentEntity();
		entity.setId(payment.getId());

		List<TPaymentListCustomEntity> list = paymentMapper.searchById(entity);

		List<TPayment> paymentList = new ArrayList<TPayment>();

		// listの各要素に対して、TPaymentを作成してコピーし、paymentListへ追加する。
		list.forEach(paymentEntity -> {
			paymentList.add(createTPayment(paymentEntity));
		});

		return paymentList;
	}

	@Override
	public void update(TPayment payment) {

		// 1. 更新対象ユーザーが存在するかチェックする
		MUserEntity userEntity = new MUserEntity() {
			{
				setId(payment.getId());
			}
		};
		MUserEntity resultEntity = userMapper.findOne(userEntity);
		if (resultEntity == null) {
			// 更新対象ユーザーがいない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0005);
		}

		// 2. 更新対象が存在するかチェックする
		TPaymentEntity targetTPaymentEntity = new TPaymentEntity();
		targetTPaymentEntity.setId(payment.getId());
		// フォーマットのため"yyyy-mm-dd"としたいので-ddの部分を付け足す
		targetTPaymentEntity.setPaymentDate(Date.valueOf(payment.getTargetPaymentDate() + "-1"));

		TPaymentEntity resultTPaymentEntity = paymentMapper.findOne(targetTPaymentEntity);
		if (resultTPaymentEntity == null) {
			// 更新対象の給与が存在しない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0008);
		}

		// 3. 更新処理を行う。
		try {
			TPaymentUpdateCustomEntity paymentCustomEntity = new TPaymentUpdateCustomEntity();
			paymentCustomEntity.setId(payment.getId());
			paymentCustomEntity.setTargetPaymentDate(Date.valueOf(payment.getTargetPaymentDate() + "-1"));
			paymentCustomEntity.setPaymentDate(Date.valueOf(payment.getPaymentDate() + "-1"));
			paymentCustomEntity.setPaymentAmount(payment.getPaymentAmount());

			paymentMapper.update(paymentCustomEntity);
		} catch (DuplicateKeyException e) {
			throw new BusinessException(MessageConstant.MSG_0007);
		}
	}

	@Override
	public void deleteOne(TPayment payment) {

		// 削除対象ユーザーが存在するかチェックする
		MUserEntity userEntity = new MUserEntity();
		userEntity.setId(payment.getId());
		MUserEntity resultUserEntity = userMapper.findOne(userEntity);
		if (resultUserEntity == null) {
			// 対象ユーザーがいない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0003);
		}

		// 削除対象レコードが存在するかチェック
		TPaymentEntity paymentEntity = createTPaymentEntity(payment);
		TPaymentEntity resultPaymentEntity = paymentMapper.findOne(paymentEntity);
		if (resultPaymentEntity == null) {
			// 対象レコードが存在しない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0003);
		}

		paymentMapper.deleteOne(paymentEntity);

	}

	private TPaymentEntity createTPaymentEntity(TPayment payment) {

		TPaymentEntity entity = new TPaymentEntity();
		entity.setId(payment.getId());
		entity.setPaymentDate(Date.valueOf(payment.getPaymentDate() + "-1"));
		entity.setPaymentAmount(payment.getPaymentAmount());

		return entity;
	}

	private TPayment createTPayment(TPaymentListCustomEntity entity) {

		TPayment payment = new TPayment();
		payment.setId(entity.getId());
		payment.setName(entity.getName());
		payment.setPaymentDate(entity.getPaymentDate().toString());
		payment.setPaymentAmount(entity.getPaymentAmount());

		return payment;
	}

}
