package jp.co.aimsoft.business.service;

import java.util.List;

import jp.co.aimsoft.business.model.TPayment;

public interface PaymentService {

	void register(TPayment attendance);

	List<TPayment> searchById(TPayment attendance);

	void update(TPayment attendance);

	void deleteOne(TPayment attendance);
}
