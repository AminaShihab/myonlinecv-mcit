package com.company.portal.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.portal.usermanagement.pojo.entity.User;

public interface UserManagementRepository extends JpaRepository<User, Long> {

}