package com.company.portal.jobmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.company.portal.common.utils.RestPreconditions;
import com.company.portal.jobmanagement.pojo.entity.MyJob;
import com.company.portal.jobmanagement.repository.MyJobRepository;

@RestController
@RequestMapping("/users")
class MyJobUserController {

	@Autowired
	MyJobRepository myJobRepository;

	// Saving a user id into job
	@PostMapping("/{id}/job/{jobId}")
	@ResponseStatus(HttpStatus.OK)
	public void applyJob(@PathVariable("id") Long id, @PathVariable("jobId") Long jobId) {
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(jobId);
		MyJob myJob = myJobRepository.findById(jobId) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		List<Long> appliedUsersid = myJob.getAppliedUsersId();
		if (appliedUsersid == null) {
			appliedUsersid = new ArrayList<Long>();
		}
		if (!appliedUsersid.contains(id)) {
			appliedUsersid.add(id);
		}
		myJob.setAppliedUsersId(appliedUsersid);
		myJobRepository.save(myJob);
	}

	// Saving a user id into job
	@DeleteMapping("/{id}/job/{jobId}")
	@ResponseStatus(HttpStatus.OK)
	public void removeJobApplication(@PathVariable("id") Long id, @PathVariable("jobId") Long jobId) {
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(jobId);
		MyJob myJob = myJobRepository.findById(jobId) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		myJob.getAppliedUsersId().remove(id);
		myJobRepository.save(myJob);
	}

}
