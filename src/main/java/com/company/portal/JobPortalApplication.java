package com.company.portal;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.company.portal.common.security.entity.PortalRoles;
import com.company.portal.common.security.entity.PortalUser;
import com.company.portal.common.security.repository.PortalUserRepository;

@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}

	@Autowired
	PortalUserRepository portalUserRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		PortalUser user = new PortalUser();
		user.setUsername("user");
		user.setPassword(new BCryptPasswordEncoder().encode("user"));
		user.setEnabled(true);
		user.setRoles(Arrays.asList(PortalRoles.PORTALUSER));
		portalUserRepository.save(user);

		PortalUser employer = new PortalUser();
		employer.setUsername("employer");
		employer.setPassword(new BCryptPasswordEncoder().encode("employer"));
		employer.setEnabled(true);
		employer.setRoles(Arrays.asList(PortalRoles.EMPLOYER));
		portalUserRepository.save(employer);
		
		
		PortalUser admin = new PortalUser();
		admin.setUsername("admin");
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		admin.setEnabled(true);
		admin.setRoles(Arrays.asList(PortalRoles.ADMIN));
		portalUserRepository.save(admin);
	}

}
