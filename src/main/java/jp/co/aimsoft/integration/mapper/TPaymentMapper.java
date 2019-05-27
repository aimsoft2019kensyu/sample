package jp.co.aimsoft.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.aimsoft.integration.entity.TPaymentEntity;
import jp.co.aimsoft.integration.entity.TPaymentListCustomEntity;
import jp.co.aimsoft.integration.entity.TPaymentUpdateCustomEntity;

@Mapper
public interface TPaymentMapper {

	int register(TPaymentEntity entity);

	TPaymentEntity findOne(TPaymentEntity entity);

	List<TPaymentListCustomEntity> searchById(TPaymentEntity entity);

	int update(TPaymentUpdateCustomEntity entity);

	int deleteOne(TPaymentEntity entity);
}
