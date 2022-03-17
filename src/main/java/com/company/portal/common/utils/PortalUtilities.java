package com.company.portal.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PortalUtilities {

	private Gson gson = new GsonBuilder().create();

	public <T> T clone(T object) {
		return object == null ? null : (T) gson.fromJson(gson.toJson(object), object.getClass());
	}
}
