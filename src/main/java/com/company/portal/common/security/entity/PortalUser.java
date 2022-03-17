package com.company.portal.common.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.company.portal.common.security.entity.converters.PortalRolesConverter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Table(name = "PORTAL_USER")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortalUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String username;
	String password;
	boolean enabled;

	@Convert(converter = PortalRolesConverter.class)
	List<PortalRoles> roles = new ArrayList<>();
}
