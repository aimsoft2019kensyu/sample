package jp.co.aimsoft.presentation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.aimsoft.business.model.MUser;
import jp.co.aimsoft.business.model.SessionBean;
import jp.co.aimsoft.business.service.UserService;
import jp.co.aimsoft.common.exception.BusinessException;
import jp.co.aimsoft.presentation.form.UserForm;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private SessionBean session;

	@RequestMapping(value = "/registration/show", method = RequestMethod.GET)
	public ModelAndView registrationShow() {

		session.setId(999999L);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new UserForm());
		modelAndView.setViewName("user/registration");

		return modelAndView;
	}

	/**
	 * ユーザー一覧表示
	 * 
	 * @param form
	 *            UserForm
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/list/show", method = RequestMethod.POST)
	public ModelAndView listShow() {

		List<MUser> list = service.findAll();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", list);
		modelAndView.setViewName("user/list");

		return modelAndView;
	}

	@RequestMapping(value = "/update/show", method = RequestMethod.GET)
	public ModelAndView updateShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new UserForm());
		modelAndView.setViewName("user/update");

		return modelAndView;
	}

	@RequestMapping(value = "/delete/show", method = RequestMethod.GET)
	public ModelAndView deleteShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new UserForm());
		modelAndView.setViewName("user/delete");

		return modelAndView;
	}

	// 以下、各種画面処理

	/**
	 * ユーザー登録
	 * 
	 * @param form
	 *            UserForm
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("form") @Valid UserForm form, BindingResult result) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		// BeanValidationによるエラーを捕捉する
		if (result.hasErrors()) {

			modelAndView.setViewName("user/registration");
			return modelAndView;
		}

		MUser user = createMUser(form);

		String errorMessage = null;

		try {
			service.register(user);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("user/registration");
		} else {
			modelAndView.setViewName("user/complete");
		}
		// セッション説明用
		modelAndView.addObject("sessionBean", session);

		return modelAndView;
	}

	/**
	 * ユーザー更新
	 * 
	 * @param form
	 *            UserForm
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute UserForm form) {

		MUser user = createMUser(form);

		String errorMessage = null;

		try {
			service.update(user);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("user/update");
		} else {
			modelAndView.setViewName("user/complete");
		}

		return modelAndView;
	}

	/**
	 * ユーザー削除
	 * 
	 * @param form
	 *            UserForm
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute UserForm form) {

		MUser user = createMUser(form);

		String errorMessage = null;

		try {
			service.deleteOne(user);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("user/delete");
		} else {
			modelAndView.setViewName("user/complete");
		}

		return modelAndView;
	}

	/**
	 * 画面データよりMUserを生成する。
	 * 
	 * @return
	 */
	private MUser createMUser(UserForm form) {

		MUser user = new MUser();
		user.setId(Long.parseLong(form.getId()));
		user.setName(form.getName());
		user.setAge(Long.parseLong(form.getAge()));
		user.setBelongGroup(form.getBelongGroup());

		return user;
	}

}
