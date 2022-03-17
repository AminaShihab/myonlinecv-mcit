package com.company.portal.cvmanagement.pojo.entity;

import java.util.ArrayList;
import java.util.List;

import com.company.portal.cvmanagement.pojo.data.Certification;
import com.company.portal.cvmanagement.pojo.data.Education;
import com.company.portal.cvmanagement.pojo.data.PersonalData;
import com.company.portal.cvmanagement.pojo.data.WorkExperience;
import com.google.gson.Gson;

class MyCVTest {
	public static void main(String[] args) {
		MyCV mycv = new MyCV();

		mycv.titleRole = "developer";
		
		List<String> myskills =new ArrayList<String>();
		myskills.add("Spring");
		myskills.add("MVC");
		
		mycv.skills = myskills;

		List<Certification> certifications = new ArrayList<>();
		Certification certification = new Certification();
		certification.setCertID("1");
		certification.setName("dddd");
		certification.setCertDetails("ggggg");
		certifications.add(certification);
		mycv.certifications = certifications;

		List<Education> educations = new ArrayList<>();
		Education education = new Education();
		education.setEdCourseName("B.Tech");
		education.setEdUniversity("MGU");
		education.setEdFromYear(null);
		education.setEdToYear(null);
		educations.add(education);
		mycv.educations = educations;

		List<WorkExperience> workexperiences = new ArrayList<>();
		WorkExperience workexperience = new WorkExperience();
		workexperience.setCompanyName("CTS");
		workexperience.setTechnologiesUsed("Java,C#,Python");
		workexperience.setProjctDetails("Worked as a java selenium tester");
		workexperience.setExpFrom(null);
		workexperience.setExpTo(null);
		workexperiences.add(workexperience);
		mycv.workExperiences = workexperiences;

		List<PersonalData> personaldatas = new ArrayList<>();
		PersonalData personaldata = new PersonalData();
		personaldata.setName("Amina");
		personaldata.setAge("30");
		personaldata.setExpectedSalary("5000");
		personaldatas.add(personaldata);
		
		PersonalData personaldata1 = new PersonalData();
		personaldata1.setName("Amina");
		personaldata1.setAge("30");
		personaldata1.setExpectedSalary("5000");
		personaldatas.add(personaldata1);
		
		mycv.personaldatas = personaldatas;

		String jsonInput = new Gson().toJson(mycv);
		System.out.println(jsonInput);

	}

}
