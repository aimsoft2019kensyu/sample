package jp.co.aimsoft.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.aimsoft.business.model.TAttendance;
import jp.co.aimsoft.business.service.AttendanceService;
import jp.co.aimsoft.common.exception.BusinessException;
import jp.co.aimsoft.presentation.form.AttendanceForm;

@Controller
@RequestMapping(value = "/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService service;

	@RequestMapping(value = "/registration/show", method = RequestMethod.GET)
	public ModelAndView registrationShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new AttendanceForm());
		modelAndView.setViewName("attendance/registration");

		return modelAndView;
	}

	@RequestMapping(value = "/list/show", method = RequestMethod.GET)
	public ModelAndView listShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("attendance/list");

		return modelAndView;
	}

	@RequestMapping(value = "/update/show", method = RequestMethod.GET)
	public ModelAndView updateShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new AttendanceForm());
		modelAndView.setViewName("attendance/update");

		return modelAndView;
	}

	@RequestMapping(value = "/delete/show", method = RequestMethod.GET)
	public ModelAndView deleteShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new AttendanceForm());
		modelAndView.setViewName("attendance/delete");

		return modelAndView;
	}

	// 以下、各種画面処理

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute AttendanceForm form) {

		TAttendance attendance = createTAttendance(form);

		String errorMessage = null;

		try {
			service.register(attendance);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("attendance/registration");
		} else {
			modelAndView.setViewName("attendance/complete");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute AttendanceForm form) {

		TAttendance attendance = createTAttendance(form);
		// 更新対象情報を追加で設定する。
		attendance.setTargetAttendanceFlg(form.getTargetAttendanceFlg());
		attendance.setTargetAttendanceDate(form.getTargetAttendanceYear() + "-" + form.getTargetAttendanceMonth() + "-"
				+ form.getTargetAttendanceDay());

		String errorMessage = null;

		try {
			service.update(attendance);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("attendance/update");
		} else {
			modelAndView.setViewName("attendance/complete");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute AttendanceForm form) {

		TAttendance attendance = createTAttendance(form);

		String errorMessage = null;

		try {
			service.deleteOne(attendance);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("attendance/delete");
		} else {
			modelAndView.setViewName("attendance/complete");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView list(@ModelAttribute AttendanceForm form) {

		TAttendance attendance = new TAttendance() {
			{
				setId(form.getId());
			}
		};

		List<TAttendance> list = service.searchById(attendance);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("attendanceList", list);
		modelAndView.setViewName("attendance/listResult");

		return modelAndView;
	}

	/**
	 * 画面データよりTAttendanceを生成する。
	 * 
	 * @return TAttendance
	 */
	private TAttendance createTAttendance(AttendanceForm form) {

		TAttendance attendance = new TAttendance();
		attendance.setId(form.getId());
		attendance.setAttendanceFlg(form.getAttendanceFlg());
		attendance.setAttendanceDate(
				form.getAttendanceYear() + "-" + form.getAttendanceMonth() + "-" + form.getAttendanceDay());
		attendance.setAttendanceTime(form.getAttendanceTime());

		return attendance;
	}

}
