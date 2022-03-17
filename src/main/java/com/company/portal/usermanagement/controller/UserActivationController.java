package com.company.portal.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.portal.common.security.entity.PortalRoles;
import com.company.portal.usermanagement.service.UserActivationService;

/*
@RestController
@RequestMapping("/email")
class UserActivationController {

	@Autowired
	private UserActivationService userActivationService;

	@PostMapping(value = "/{userType}/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void resendEmail(@PathVariable("userType") PortalRoles userType, @PathVariable("id") Long id) {
		userActivationService.resendEmail(userType, id);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void activateUser(@RequestBody String secretKey) {
		userActivationService.activateEmail(secretKey);
	}

}
*/