package com.company.portal.jobmanagement.pojo.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MyJob {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long jobId; 
	private String jobTitle;
	private String jobDescription;
	private Integer salary;
	private String jobType;
	private String requiredSkills;
	private String companyName;
	private String companyDesciption;
	private String city;
	private Date validTill;
	private LocalDate postedDate = LocalDate.now(ZoneId.of("UTC-05:00"));	
	private Long postedById;	
	
	@ElementCollection(targetClass = Long.class)
	List<Long> appliedUsersId=new ArrayList<>();
		

}
