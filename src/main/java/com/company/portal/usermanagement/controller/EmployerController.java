package com.company.portal.usermanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.company.portal.common.security.entity.PortalRoles;
import com.company.portal.common.security.entity.PortalUser;
import com.company.portal.common.security.repository.PortalUserRepository;
import com.company.portal.common.utils.RestPreconditions;
import com.company.portal.usermanagement.pojo.entity.Employer;

@RestController
@RequestMapping("/employers")
class EmployerController {

	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private PortalUserRepository portalUserRepository;

	@GetMapping
	public List<Employer> findAll() {
		return employerRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Employer findById(@PathVariable("id") Long id) {
		return employerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employer create(@RequestBody Employer resource) {
		RestPreconditions.checkNotNull(resource);
		
		PortalUser employer = new PortalUser();
		employer.setUsername(resource.getUserName());
		employer.setPassword(new BCryptPasswordEncoder().encode(resource.getPassword()));
		employer.setEnabled(true);
		employer.setRoles(Arrays.asList(PortalRoles.EMPLOYER));		
		portalUserRepository.save(employer);
		
		resource.setUserName("@Refer portal user");
		resource.setPassword("@Refer portal user");
		resource.setId(employer.getId());	

		return employerRepository.save(resource);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") Long id, @RequestBody Employer resource) {
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(resource);
		RestPreconditions.checkNotNull(employerRepository.findById(resource.getId()));
		resource.setId(id);
		employerRepository.save(resource);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		employerRepository.deleteById(id);
	}
}

interface EmployerRepository extends JpaRepository<Employer, Long> {

}