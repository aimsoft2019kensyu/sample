package jp.co.aimsoft.common.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD }) // フィールドに有効なアノテーション
@Retention(RetentionPolicy.RUNTIME) // 保持されるスコープ。よくわからないけどRUNTIMEで安定。
@Documented // javadocアノテーションにアノテーションのリンクを張れるようになる。
@Constraint(validatedBy = {})
@Pattern(regexp = "[^0-9０-９]+", message = "{aimsoft.notnumeric.message}")
public @interface NotNumeric {

	String message() default "{aimsoft.notnumeric.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * よくわからないけど必要そうなので追加
	 */
	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		NotNumeric[] value();
	}
}
