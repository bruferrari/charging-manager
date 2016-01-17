package com.ferrarib.charging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferrarib.charging.model.Title;

public interface Titles extends JpaRepository<Title, Long> {

}
