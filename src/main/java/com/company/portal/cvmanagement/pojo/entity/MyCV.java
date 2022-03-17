package com.company.portal.cvmanagement.pojo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.company.portal.cvmanagement.pojo.data.Certification;
import com.company.portal.cvmanagement.pojo.data.Education;
import com.company.portal.cvmanagement.pojo.data.PersonalData;
import com.company.portal.cvmanagement.pojo.data.WorkExperience;
import com.company.portal.cvmanagement.pojo.data.converters.CertificationsConverter;
import com.company.portal.cvmanagement.pojo.data.converters.EducationsConverter;
import com.company.portal.cvmanagement.pojo.data.converters.PersonalDataConverter;
import com.company.portal.cvmanagement.pojo.data.converters.WorkExperienceConverter;

import lombok.Data;
@Entity
@Data
public class MyCV {

	@Id
	private Long userId;

	String titleRole;

	// String profilePicture . There should be a folder to keep the profile picture.
	// if there is a picture uploaded then the profile picture will be available
	// with the user id. example 12.jpg- when userid==12

	@ElementCollection(targetClass = String.class)
	List<String> skills;

	@Lob
	@Column(name = "work_experiences", columnDefinition = "BLOB")
	@Convert(converter = WorkExperienceConverter.class)
	List<WorkExperience> workExperiences;

	@Lob
	@Column(name = "education", columnDefinition = "BLOB")
	@Convert(converter = EducationsConverter.class)
	List<Education> educations;

	@Lob
	@Column(name = "certifications", columnDefinition = "BLOB")
	@Convert(converter = CertificationsConverter.class)
	List<Certification> certifications;
	
	@Lob
	@Column(name = "personaldatas", columnDefinition = "BLOB")
	@Convert(converter = PersonalDataConverter.class)
	List<PersonalData> personaldatas;

}