package org.soa.administratorService.repository;

import org.soa.administratorService.vao.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> { }
