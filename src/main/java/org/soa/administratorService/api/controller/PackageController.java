package org.soa.administratorService.api.controller;

import org.soa.administratorService.dto.PackageDTO;
import org.soa.administratorService.dto.ServicePackageDTO;
import org.soa.administratorService.service.PackageService;
import org.soa.administratorService.service.ServicePackageService;
import org.soa.administratorService.vao.Package;
import org.soa.administratorService.vao.ServicePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private ServicePackageService servicePackageService;

    @GetMapping
    public ResponseEntity<List<Package>> getAllPackages() {
        List<Package> packages = packageService.getAll();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable Long id) {
        Package Package = packageService.get(id);
        return ResponseEntity.ok(Package);
    }

    @PostMapping
    public ResponseEntity<Package> createPackage(@RequestBody PackageDTO packageDTO) {
        Package createdPackage = packageService.create(
                packageDTO.getName(),
                packageDTO.getDescription(),
                packageDTO.getDuration(),
                packageDTO.getDurationPeriod()
        );
        return ResponseEntity.ok(createdPackage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable Long id, @RequestBody PackageDTO packageDTO) {
        Package updatedPackage = packageService.update(id,
                packageDTO.getName(),
                packageDTO.getDescription(),
                packageDTO.getDuration(),
                packageDTO.getDurationPeriod()
        );
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        packageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/service")
    public ResponseEntity<ServicePackage> addServiceToPackage(@RequestBody ServicePackageDTO servicePackageDTO) {
        ServicePackage createdPackage = servicePackageService.create(
                servicePackageDTO.getServiceId(),
                servicePackageDTO.getPackageId(),
                servicePackageDTO.getTimeFrom(),
                servicePackageDTO.getTimeTo());
        return ResponseEntity.ok(createdPackage);
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServicePackage>> getAllServicesOfPackage(@PathVariable Long id) {
        List<ServicePackage> servicePackages = servicePackageService.getServicesByPackage(id);
        return ResponseEntity.ok(servicePackages);
    }

    @PutMapping("/{id}/service/{serviceId}")
    public ResponseEntity<ServicePackage> updatePackage(@PathVariable Long id, @PathVariable Long serviceId, @RequestBody ServicePackageDTO packageDTO) {
        ServicePackage updatedPackage = servicePackageService.update(
                id,
                serviceId,
                packageDTO.getPackageId(),
                packageDTO.getTimeFrom(),
                packageDTO.getTimeTo()
        );
        return ResponseEntity.ok(updatedPackage);
    }
    @DeleteMapping("/service/{id}")
    public ResponseEntity<Void> deleteServiceFromPackage(@PathVariable Long id) {
        servicePackageService.delete(id);
        return ResponseEntity.noContent().build();
    }


}