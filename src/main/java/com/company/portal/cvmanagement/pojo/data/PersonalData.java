package com.company.portal.cvmanagement.pojo.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonalData {

	String name;
	String age;
	String expectedSalary;

}
