package com.company.portal.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.portal.common.security.entity.PortalRoles;
import com.company.portal.common.security.entity.PortalUser;
import com.company.portal.common.utils.RestPreconditions;

import lombok.Data;

/*
@RestController
@RequestMapping("/login")
class LoginCredentialsController {

	@Autowired
	private LoginCredentialsRepository loginService;

	@PutMapping(value = "/{role}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("role") PortalRoles role, @PathVariable("id") Long id,
			@RequestBody PasswordRest resource) {
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(role);
		RestPreconditions.checkNotNull(resource);
		PortalUser existingcredentials = loginService.findByIdAndUserType(id, role);
		RestPreconditions.checkNotNull(existingcredentials);
		RestPreconditions.checkEqual(resource.oldPassword, resource.newPassword);
		existingcredentials.setPassword(resource.newPassword);
		loginService.save(existingcredentials);
	}

}

@Data
class PasswordRest {
	String oldPassword;
	String newPassword;
}

  interface LoginCredentialsRepository extends JpaRepository<PortalUser, Long> {
 

	PortalUser findByIdAndUserType(Long id, PortalRoles role);

}*/