package jp.co.aimsoft.business.service;

import java.util.List;

import jp.co.aimsoft.business.model.MUser;
import jp.co.aimsoft.common.constants.ProcessingResult;

public interface UserService {

	ProcessingResult register(MUser user);

	List<MUser> findAll();

	ProcessingResult update(MUser user);

	ProcessingResult deleteOne(MUser user);
}
