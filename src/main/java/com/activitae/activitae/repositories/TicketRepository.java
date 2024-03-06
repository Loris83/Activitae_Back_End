package com.activitae.activitae.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitae.activitae.entities.Chat;
import com.activitae.activitae.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	Optional<Ticket> findById(Long id);
}
