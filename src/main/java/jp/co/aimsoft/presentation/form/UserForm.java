package jp.co.aimsoft.presentation.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jp.co.aimsoft.common.constraint.HalfWidthCharacter;
import jp.co.aimsoft.common.constraint.NotNumeric;
import jp.co.aimsoft.common.constraint.Numeric;

public class UserForm {

	@NotEmpty
	@Numeric
	@Size(min = 1, max = 3)
	private String id;

	@NotEmpty
	@NotNumeric
	@Size(min = 1, max = 20)
	private String name;

	@HalfWidthCharacter(message = "半角文字で入力して")
	private String age;

	@NotNumeric
	@Size(min = 1, max = 50, message = "1-20文字で入力しなさい")
	private String belongGroup;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBelongGroup() {
		return belongGroup;
	}

	public void setBelongGroup(String group) {
		this.belongGroup = group;
	}

}
