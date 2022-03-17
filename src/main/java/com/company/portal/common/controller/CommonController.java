package com.company.portal.common.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.portal.jobmanagement.pojo.entity.MyJob;
import com.company.portal.jobmanagement.repository.MyJobRepository;

@RestController
@RequestMapping("/job")
public class CommonController {

	@Autowired
	MyJobRepository myJobRepository;

	@GetMapping
	public List<MyJob> findAll() {
		return IteratorUtils.toList(myJobRepository.findAll().iterator());
	}
	@GetMapping("/{jobId}")
	public ResponseEntity<MyJob> findByUserId(@PathVariable Long jobId) {
		Optional<MyJob> myjob = myJobRepository.findById(jobId);
		if (myjob.isPresent()) {
			return ResponseEntity.ok(myjob.get());
		}
		return new ResponseEntity<MyJob>(HttpStatus.NOT_FOUND);
	}

}
