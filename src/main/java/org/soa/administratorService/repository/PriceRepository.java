package org.soa.administratorService.repository;

import org.soa.administratorService.vao.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
