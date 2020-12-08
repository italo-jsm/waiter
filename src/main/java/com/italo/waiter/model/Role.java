package com.italo.waiter.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Role extends AbstractEntity{
	private String name;
}
