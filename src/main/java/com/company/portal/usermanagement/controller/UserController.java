package com.company.portal.usermanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.company.portal.usermanagement.pojo.entity.User;
import com.company.portal.usermanagement.repository.UserManagementRepository;

@RestController
@RequestMapping("/users")
class UserController {

	@Autowired
	private UserManagementRepository userRepository;
	
	@Autowired
	private PortalUserRepository portalUserRepository;

	@GetMapping
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable("id") Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User resource) {
		RestPreconditions.checkNotNull(resource);
		
		PortalUser user = new PortalUser();
		user.setUsername(resource.getUserName());
		user.setPassword(new BCryptPasswordEncoder().encode(resource.getPassword()));
		user.setEnabled(true);
		user.setRoles(Arrays.asList(PortalRoles.PORTALUSER));
		portalUserRepository.save(user);
		
		resource.setUserName("@Refer portal user");
		resource.setPassword("@Refer portal user");
		resource.setId(user.getId());	
		
		return userRepository.save(resource);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") Long id, @RequestBody User resource) {
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(resource);
		RestPreconditions.checkNotNull(userRepository.findById(resource.getId()));
		resource.setId(id);
		userRepository.save(resource);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
	}

}
