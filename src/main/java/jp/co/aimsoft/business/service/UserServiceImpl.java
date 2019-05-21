package jp.co.aimsoft.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import jp.co.aimsoft.business.model.MUser;
import jp.co.aimsoft.common.constants.MessageConstant;
import jp.co.aimsoft.common.exception.BusinessException;
import jp.co.aimsoft.integration.entity.MUserEntity;
import jp.co.aimsoft.integration.mapper.MUserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MUserMapper mapper;

	@Override
	public void register(MUser user) {

		MUserEntity entity = createMUserEntity(user);

		try {
			mapper.register(entity);
		} catch (DuplicateKeyException e) {
			// 一意制約違反発生時エラーを捕捉しエラー文言を書き換える
			throw new BusinessException(MessageConstant.MSG_0001);
		}
	}

	@Override
	public List<MUser> findAll() {

		List<MUserEntity> list = mapper.findAll();

		List<MUser> mUserList = new ArrayList<MUser>();

		// listの各要素に対して、MUserを作成してコピーし、mUserListへ追加する。
		list.forEach(mUserEntity -> {
			mUserList.add(createMUser(mUserEntity));
		});

		return mUserList;
	}

	@Override
	public void update(MUser user) {

		MUserEntity entity = createMUserEntity(user);

		// 更新対象ユーザーが存在するかチェックする
		MUserEntity resultEntity = mapper.findOne(entity);
		if (resultEntity == null) {
			// 更新対象ユーザーがいない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0002);
		}

		mapper.update(entity);
	}

	@Override
	public void deleteOne(MUser user) {

		MUserEntity entity = createMUserEntity(user);

		// 削除対象ユーザーが存在するかチェックする
		MUserEntity resultEntity = mapper.findOne(entity);
		if (resultEntity == null) {
			// 削除対象ユーザーがいない場合はエラーとする。
			throw new BusinessException(MessageConstant.MSG_0003);
		}

		mapper.deleteOne(entity);
	}

	/**
	 * MUserよりMUserEntityへデータをコピーし返却する
	 * 
	 * @param user
	 *            MUser
	 * @return MUserEntity
	 */
	private MUserEntity createMUserEntity(MUser user) {

		MUserEntity entity = new MUserEntity();
		entity.setId(user.getId());
		entity.setName(user.getName());
		entity.setAge(user.getAge());
		entity.setBelongGroup(user.getBelongGroup());

		return entity;
	}

	/**
	 * MUserEntityよりMUserへデータをコピーし返却する
	 * 
	 * @param entity
	 * @return
	 */
	private MUser createMUser(MUserEntity entity) {

		MUser user = new MUser();
		user.setId(entity.getId());
		user.setName(entity.getName());
		user.setAge(entity.getAge());
		user.setBelongGroup(entity.getBelongGroup());

		return user;
	}

}
