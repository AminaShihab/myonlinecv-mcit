package com.company.portal.cvmanagement.pojo.data;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Education {

	String edCourseName;
	String edUniversity;
	String edGrade;
	Date edFromYear;
	Date edToYear;
	
}
