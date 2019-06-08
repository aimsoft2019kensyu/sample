package jp.co.aimsoft.common.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericConstraint implements ConstraintValidator<Numeric, String> {

	public void initialize(Numeric constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		return value.matches("^[0-9]+$");
	}

}
