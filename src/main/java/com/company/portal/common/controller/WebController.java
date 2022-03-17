package com.company.portal.common.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.portal.common.security.entity.PortalUser;
import com.company.portal.jobmanagement.pojo.entity.MyJob;
import com.company.portal.jobmanagement.repository.MyJobRepository;

import lombok.Generated;

@Generated
@Controller
public class WebController {

	@Autowired
	MyJobRepository myJobRepository;

	@GetMapping("/")
	public String startpage(Principal principal)  {
		return principal == null ? "index" : "dashboard";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/editprofile")
	public String editprofile() {
		return "editprofile";
	}

	@PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYER')")
	@GetMapping("/createjob") // path
	public String createjob() {
		return "createjob"; // page
	}

	@PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYER')")
	@GetMapping("/appliedusers") // path
	public String appliedusers() {
		return "appliedusers"; // page
	}

	@PreAuthorize("hasAnyAuthority('ADMIN','PORTALUSER')")
	@GetMapping("/createresume") // path
	public String createresume() {
		return "createresume"; // page
	}

	private static final Integer DEFAULT_PAGE_SIZE = 5;

	@GetMapping("/listjobs")
	public String listBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Page<MyJob> pageData = myJobRepository.findAllByPostedById(getUserId(),
				PageRequest.of(page.orElse(1) - 1, size.orElse(DEFAULT_PAGE_SIZE)));
		doPagination("jobPage", model, pageData);
		return "listJobs";
	}
	@GetMapping("/alljobs")
	public String alljobs(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<MyJob> bookPage = myJobRepository.findAll(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("alljobs", bookPage);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "alljobs";
	}

	@GetMapping("/applyjob")
	public String applyjob(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Stream<MyJob> submittedJobs = myJobRepository.findAllByAppliedUsersIdIn(Arrays.asList(getUserId()),
				PageRequest.of(page.orElse(1) - 1, size.orElse(DEFAULT_PAGE_SIZE))).get();
		List<Long> submittedJobIds = submittedJobs.map(j -> j.getJobId()).collect(Collectors.toList());
		if (submittedJobIds.isEmpty()) {
			// fix for framework issue
			submittedJobIds.add(-1l);
		}
		Page<MyJob> pageData = myJobRepository.findAllByJobIdIsNotIn(submittedJobIds,
				PageRequest.of(page.orElse(1) - 1, size.orElse(DEFAULT_PAGE_SIZE)));
		doPagination("applyjob", model, pageData);
		return "applyjob";
	}

	@GetMapping("/appliedjobs")
	public String appliedjobs(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Page<MyJob> pageData = myJobRepository.findAllByAppliedUsersIdIn(Arrays.asList(getUserId()),
				PageRequest.of(page.orElse(1) - 1, size.orElse(DEFAULT_PAGE_SIZE)));
		model.addAttribute("appliedjobs", pageData);
		doPagination("appliedjobs", model, pageData);
		return "appliedjobs";
	}

	@GetMapping("/jobappliedusers")
	public String jobappliedusers(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Page<MyJob> pageData = myJobRepository.findAllByPostedById(getUserId(),
				PageRequest.of(page.orElse(1) - 1, size.orElse(DEFAULT_PAGE_SIZE)));
		doPagination("jobappliedusers", model, pageData);
		return "jobappliedusers";
	}

	private long getUserId() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		PortalUser sessionUser = (PortalUser) servletRequestAttributes.getRequest().getSession()
				.getAttribute("PORTAL_USER");
		return sessionUser.getId();
	}

	<T> void doPagination(String pageName, Model model, Page<T> pageData) {
		model.addAttribute(pageName, pageData);
		int totalPages = pageData.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
	}
}