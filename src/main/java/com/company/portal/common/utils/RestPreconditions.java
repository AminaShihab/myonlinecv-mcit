package com.company.portal.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestPreconditions {
	public <T> T checkFound(T resource) {
		if (resource == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return resource;
	}

	public <T> void checkNotNull(T resource) {
		if (resource == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	public void checkEqual(String leftsideArgument, String rightsideArgument) {
		if (!StringUtils.equals(leftsideArgument, rightsideArgument)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}