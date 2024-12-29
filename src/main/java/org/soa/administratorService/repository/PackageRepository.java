package org.soa.administratorService.repository;

import org.soa.administratorService.vao.Package;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
