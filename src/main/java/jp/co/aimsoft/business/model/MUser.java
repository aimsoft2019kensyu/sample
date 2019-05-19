package jp.co.aimsoft.business.model;

public class MUser {

	private Long id;

	private String name;

	private Long age;

	private String belongGroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getBelongGroup() {
		return belongGroup;
	}

	public void setBelongGroup(String group) {
		this.belongGroup = group;
	}
}
