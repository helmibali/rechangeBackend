package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository <Contact, Long> {
}
