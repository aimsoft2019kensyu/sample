package jp.co.aimsoft.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.aimsoft.business.model.TPayment;
import jp.co.aimsoft.business.service.PaymentService;
import jp.co.aimsoft.common.exception.BusinessException;
import jp.co.aimsoft.presentation.form.PaymentForm;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private PaymentService service;

	@RequestMapping(value = "/registration/show", method = RequestMethod.GET)
	public ModelAndView registrationShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new PaymentForm());
		modelAndView.setViewName("payment/registration");

		return modelAndView;
	}

	@RequestMapping(value = "/list/show", method = RequestMethod.GET)
	public ModelAndView listShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("payment/list");

		return modelAndView;
	}

	@RequestMapping(value = "/update/show", method = RequestMethod.GET)
	public ModelAndView updateShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new PaymentForm());
		modelAndView.setViewName("payment/update");

		return modelAndView;
	}

	@RequestMapping(value = "/delete/show", method = RequestMethod.GET)
	public ModelAndView deleteShow() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", new PaymentForm());
		modelAndView.setViewName("payment/delete");

		return modelAndView;
	}

	// 以下、各種画面処理

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute PaymentForm form) {

		TPayment payment = createTPayment(form);

		String errorMessage = null;

		try {
			service.register(payment);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("payment/registration");
		} else {
			modelAndView.setViewName("payment/complete");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute PaymentForm form) {

		TPayment payment = createTPayment(form);
		// 更新対象情報を追加で設定する。
		payment.setTargetPaymentDate(form.getTargetPaymentYear() + "-" + form.getTargetPaymentMonth());

		String errorMessage = null;

		try {
			service.update(payment);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("payment/update");
		} else {
			modelAndView.setViewName("payment/complete");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute PaymentForm form) {

		TPayment payment = createTPayment(form);

		String errorMessage = null;

		try {
			service.deleteOne(payment);
		} catch (BusinessException e) {
			errorMessage = e.getMessage();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("form", form);

		if (errorMessage != null) {
			// エラーの場合は再表示
			modelAndView.addObject("errorMessage", errorMessage);
			modelAndView.setViewName("payment/delete");
		} else {
			modelAndView.setViewName("payment/complete");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView list(@ModelAttribute PaymentForm form) {

		TPayment payment = new TPayment() {
			{
				setId(form.getId());
			}
		};

		List<TPayment> list = service.searchById(payment);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("paymentList", list);
		modelAndView.setViewName("payment/listResult");

		return modelAndView;
	}

	/**
	 * 画面データよりTPaymentを生成する。
	 * 
	 * @return TPayment
	 */
	private TPayment createTPayment(PaymentForm form) {

		TPayment payment = new TPayment();
		payment.setId(form.getId());
		payment.setPaymentDate(form.getPaymentYear() + "-" + form.getPaymentMonth());
		payment.setPaymentAmount(form.getPaymentAmount());

		return payment;
	}

}
