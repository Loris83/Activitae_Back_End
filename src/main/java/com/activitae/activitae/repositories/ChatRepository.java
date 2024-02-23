package com.activitae.activitae.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitae.activitae.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	Optional<Chat> findById(Long id);
}
