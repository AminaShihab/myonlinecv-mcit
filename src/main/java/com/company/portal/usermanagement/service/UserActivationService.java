package com.company.portal.usermanagement.service;

import com.company.portal.common.security.entity.PortalRoles;

public interface UserActivationService {

	void activateEmail(String secretKey);

	void resendEmail(PortalRoles role, Long id);

}
