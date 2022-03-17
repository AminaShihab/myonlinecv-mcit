package com.company.portal.jobmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.company.portal.jobmanagement.pojo.entity.MyJob;
import com.company.portal.jobmanagement.repository.MyJobRepository;

@RestController
@RequestMapping("/employers/job")
public class MyJobController {

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
 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MyJob createMyJob(@RequestBody MyJob myJOB) {
		return myJobRepository.save(myJOB);
	}

	@PutMapping("/{jobId}")
	public ResponseEntity<MyJob> updateMyJobs(@PathVariable Long jobId, @RequestBody MyJob myJOB) {
		if (myJobRepository.existsById(jobId)) {
			myJOB.setJobId(jobId);
			myJobRepository.save(myJOB);
			return ResponseEntity.ok(myJOB);
		}
		return new ResponseEntity<MyJob>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{jobId}")
	public ResponseEntity<Void> deleteMyJobs(@PathVariable Long jobId) {
		myJobRepository.deleteById(jobId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
