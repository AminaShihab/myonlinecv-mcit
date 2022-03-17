package com.company.portal.jobmanagement.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.portal.jobmanagement.pojo.entity.MyJob;

public interface MyJobRepository extends PagingAndSortingRepository<MyJob, Long> {

	Page<MyJob> findAllByAppliedUsersIdIn(Collection<Long> appliedUsersId, Pageable pageable);

	Page<MyJob> findAllByJobIdIsNotIn(Collection<Long> jobIds, Pageable pageable);
	
	Page<MyJob> findAllByPostedById(Long postedById, Pageable pageable);

	
	

}