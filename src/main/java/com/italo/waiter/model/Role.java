package com.italo.waiter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role extends AbstractEntity{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(String name) {
		this.name = name;
	}

	public Role() {
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + this.getId().toString() + '\'' +
				"name='" + name + '\'' +
				'}';
	}
}
