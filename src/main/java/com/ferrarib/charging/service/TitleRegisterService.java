package com.ferrarib.charging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ferrarib.charging.model.Title;
import com.ferrarib.charging.repository.Titles;

@Service
public class TitleRegisterService {

	@Autowired
	private Titles titles;
	
	public void save(Title title) {
		try {
			titles.save(title);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
	}

	public void remove(Long id) {
		titles.delete(id);
	}
	
}
