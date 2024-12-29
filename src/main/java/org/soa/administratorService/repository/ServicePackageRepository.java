package org.soa.administratorService.repository;

import org.soa.administratorService.vao.ServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicePackageRepository extends JpaRepository<ServicePackage, Long> {

    @Query("SELECT sp FROM ServicePackage sp WHERE sp.service.id = ?1")
    List<ServicePackage> findByServiceId(Long id);

    @Query("SELECT sp FROM ServicePackage sp WHERE sp.aPackage.id = ?1")
    List<ServicePackage> findByPackageId(Long id);
}
