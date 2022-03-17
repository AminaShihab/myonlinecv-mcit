package com.company.portal.cvmanagement.service;

/*
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import com.company.portal.cvmanagement.pojo.entity.MyCV;

public class MyCVServiceImpl implements MyCVService {

	private MyCVRepository myCVRepository;

	public MyCVServiceImpl(MyCVRepository myCVRepository) {
		this.myCVRepository = myCVRepository;
	}

	@Override
	public MyCV findByUserId(Long userId) {
		Optional<MyCV> mycv = myCVRepository.findById(userId);
		if (mycv.isPresent()) {
			return mycv.get();
		}
		throw new RuntimeException(HttpStatus.NOT_FOUND.name());
	}

	@Override
	public MyCV save(Long userId, MyCV myCV) {
		myCV.setUserId(userId);
		myCVRepository.save(myCV);
		return myCV;
	}

	@Override
	public MyCV update(Long userId, MyCV myCV) {
		if (myCVRepository.existsById(userId)) {
			myCV.setUserId(userId);
			myCVRepository.save(myCV);
			return myCV;
		}
		throw new RuntimeException(HttpStatus.NOT_FOUND.name());
	}

	@Override
	public void deleteById(Long userId) {
		myCVRepository.deleteById(userId);
	}

}

interface MyCVRepository extends JpaRepository<MyCV, Long> {

}*/