package jp.co.aimsoft.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.aimsoft.integration.entity.MUserEntity;

@Mapper
public interface MUserMapper {

	int register(MUserEntity entity);

	MUserEntity findOne(MUserEntity entity);

	List<MUserEntity> findAll();

	int update(MUserEntity entity);

	int deleteOne(MUserEntity entity);
}
