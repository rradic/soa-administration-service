package org.soa.administratorService.repository;

import org.soa.administratorService.vao.CompanyService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyServiceRepository extends JpaRepository<CompanyService, Long> {
}
