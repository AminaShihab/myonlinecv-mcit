package com.company.portal.usermanagement.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActivation {

	// employeeid or employerid
	@Id
	Long id;

	String activationCode;
}
