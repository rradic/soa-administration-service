package org.soa.administratorService.repository;

import org.soa.administratorService.vao.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
