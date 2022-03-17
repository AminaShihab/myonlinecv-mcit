package com.company.portal.usermanagement.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employer {

	@Id
	Long id;
	String companyName;
	String userName;
	String email;
	String password;
	String address;
	String contact;
	String role;

}
