package com.company.portal.cvmanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.portal.cvmanagement.pojo.entity.MyCV;
import com.company.portal.usermanagement.repository.UserManagementRepository;

@RestController
@RequestMapping("users/cv")
public class MyCVController {

	@Autowired
	MyCVRepository myCVRepository;
	@Autowired
	UserManagementRepository userManagementRepository;

	@GetMapping("/{userId}")
	public ResponseEntity<MyCV> findByUserId(@PathVariable Long userId) {
		Optional<MyCV> mycv = myCVRepository.findById(userId);
		if (mycv.isPresent()) {
			return ResponseEntity.ok(mycv.get());
		}
		return new ResponseEntity<MyCV>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MyCV> createMyCV(@PathVariable Long userId, @RequestBody MyCV myCV) {
		if (userManagementRepository.findById(userId).isPresent()) {
			myCV.setUserId(userId);
			myCVRepository.save(myCV);
			return new ResponseEntity<MyCV>(myCV, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<MyCV>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<MyCV> updateMyCVs(@PathVariable Long userId, @RequestBody MyCV myCV) {
		if (myCVRepository.existsById(userId)) {
			myCV.setUserId(userId);
			myCVRepository.save(myCV);
			return ResponseEntity.ok(myCV);
		}
		return new ResponseEntity<MyCV>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteMyCVs(@PathVariable Long userId) {
		myCVRepository.deleteById(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

interface MyCVRepository extends JpaRepository<MyCV, Long> {

}