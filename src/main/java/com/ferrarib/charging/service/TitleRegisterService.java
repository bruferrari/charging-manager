package com.ferrarib.charging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ferrarib.charging.model.Title;
import com.ferrarib.charging.model.TitleStatus;
import com.ferrarib.charging.repository.Titles;
import com.ferrarib.charging.repository.filter.TitleFilter;

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

	public String receive(Long id) {
		Title title = titles.findOne(id);
		title.setStatus(TitleStatus.RECEIVED);
		titles.save(title);
		
		return TitleStatus.RECEIVED.getDescription();
	}
	
	public List<Title> filter(TitleFilter filter) {
		String desc = filter.getDescription() == null ? "%" : filter.getDescription();
		return titles.findByDescriptionContaining(desc);
	}
}
