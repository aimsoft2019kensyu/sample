package jp.co.aimsoft.business.service;

import java.util.List;

import jp.co.aimsoft.business.model.MUser;

public interface UserService {

	void register(MUser user);

	List<MUser> findAll();

	void update(MUser user);

	void deleteOne(MUser user);
}
