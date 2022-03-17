package com.company.portal.common.security.entity.converters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

import com.company.portal.common.security.entity.PortalRoles;

public class PortalRolesConverter implements AttributeConverter<List<PortalRoles>, String> {

	@Override
	public String convertToDatabaseColumn(List<PortalRoles> attribute) {
		if (attribute == null) {
			return null;
		}
		String commaSeperatedRole = attribute.stream().map(t -> t.name()).collect(Collectors.joining(","));
		return commaSeperatedRole;
	}

	@Override
	public List<PortalRoles> convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return Arrays.asList(dbData.split(",")).stream().map(d -> PortalRoles.valueOf(d)).collect(Collectors.toList());
	}

}