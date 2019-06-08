package jp.co.aimsoft.common.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HalfWidthCharacterConstraint implements ConstraintValidator<HalfWidthCharacter, String> {

	public void initialize(HalfWidthCharacter constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		return value.matches("[-_@+*;:#$%&A-Za-z0-9]+");
	}

}
