package com.company.portal.cvmanagement.pojo.data;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkExperience {

	String companyName;
	String technologiesUsed;
	String projctDetails;
	Date expFrom;
	Date expTo;

}