package com.company.portal.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;

import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public interface BaseConverter<T> extends AttributeConverter<List<T>, String> {
	final Gson GSON = new GsonBuilder().create();

	public default String convertToDatabaseColumn(List<T> dataList) {
		if (dataList != null) {
			return GSON.toJson(dataList);
		}
		return null;
	}

	public default List<T> convertToEntityAttribute(String dbData) {
		if (!ObjectUtils.isEmpty(dbData)) {
			return GSON.fromJson(dbData, new TypeToken<ArrayList<T>>() {
			}.getType());
		}
		return Collections.emptyList();
	}
}
