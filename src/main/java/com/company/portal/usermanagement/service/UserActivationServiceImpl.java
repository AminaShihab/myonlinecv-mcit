package com.company.portal.usermanagement.service;

import org.springframework.stereotype.Service;

import com.company.portal.common.security.entity.PortalRoles;

@Service
public class UserActivationServiceImpl implements UserActivationService {

	@Override
	public void activateEmail(String secretKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resendEmail(PortalRoles role, Long id) {
		// TODO Auto-generated method stub

	}

}
