package com.company.portal.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.portal.common.security.common.PortalUserDetails;
import com.company.portal.common.security.entity.PortalUser;
import com.company.portal.common.security.repository.PortalUserRepository;
import com.company.portal.common.utils.PortalUtilities;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PortalUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PortalUser user = userRepository.getPortalUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		// Setting PORTAL_USER for UI in session check header.html for further details
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		PortalUser sessionUser = PortalUtilities.clone(user);
		sessionUser.setPassword("[You Can't get My Password]");
		servletRequestAttributes.getRequest().getSession().setAttribute("PORTAL_USER", sessionUser);
		
		return new PortalUserDetails(user);
	}
}
