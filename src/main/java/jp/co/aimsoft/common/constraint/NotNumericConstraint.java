package jp.co.aimsoft.common.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNumericConstraint implements ConstraintValidator<NotNumeric, String> {

	public void initialize(NotNumeric constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		return value.matches("^[^0-9]+$");
	}

}
