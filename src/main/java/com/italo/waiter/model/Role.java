package com.italo.waiter.model;

import javax.persistence.Entity;

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
}
