package com.company.portal.common.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.portal.common.security.entity.PortalUser;

public interface PortalUserRepository extends CrudRepository<PortalUser, Long> {
	public PortalUser getPortalUserByUsername(String username);
}
