package org.soa.administratorService.repository;

import org.soa.administratorService.vao.ServiceVao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceVao, Long> {
}
