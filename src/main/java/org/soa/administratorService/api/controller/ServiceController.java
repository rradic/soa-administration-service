package org.soa.administratorService.api.controller;

import org.soa.administratorService.dto.ServiceDTO;
import org.soa.administratorService.dto.ServicePackageDTO;
import org.soa.administratorService.service.ServicePackageService;
import org.soa.administratorService.service.ServiceService;
import org.soa.administratorService.vao.ServicePackage;
import org.soa.administratorService.vao.ServiceVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServicePackageService servicePackageService;

    @GetMapping
    public ResponseEntity<List<ServiceVao>> getAllServices() {
        List<ServiceVao> services = serviceService.getAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceVao> getServiceById(@PathVariable Long id) {
        ServiceVao ServiceVao = serviceService.get(id);
        return ResponseEntity.ok(ServiceVao);
    }

    @PostMapping
    public ResponseEntity<ServiceVao> createService(@RequestBody ServiceDTO request) {
        ServiceVao createdService = serviceService.create(
                request.getName(),
                request.getDescription(),
                request.getDuration(),
                request.getDurationPeriod(),
                request.getIdServiceCategory()
        );
        return ResponseEntity.ok(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceVao> updateService(@PathVariable Long id, @RequestBody ServiceDTO request) {
        ServiceVao updatedService = serviceService.update(id,
                request.getName(),
                request.getDescription(),
                request.getDuration(),
                request.getDurationPeriod(),
                request.getIdServiceCategory()
        );
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/package")
    public ResponseEntity<ServicePackage> addServiceToPackage(@RequestBody ServicePackageDTO request) {
        ServicePackage createdService = servicePackageService.create(
                request.getServiceId(),
                request.getPackageId(),
                request.getTimeFrom(),
                request.getTimeTo()
        );
        return ResponseEntity.ok(createdService);
    }

    @GetMapping("/package/{id}")
    public ResponseEntity<List<ServicePackage>> getAllPackagesForService(@PathVariable Long id) {
        List<ServicePackage> services = servicePackageService.getAll();
        return ResponseEntity.ok(services);
    }

    @PutMapping("/package/{id}")
    public ResponseEntity<ServicePackage> updateServicePackage(@PathVariable Long id, @RequestBody ServicePackageDTO request) {
        ServicePackage updatedService = servicePackageService.update(id,
                request.getServiceId(),
                request.getPackageId(),
                request.getTimeFrom(),
                request.getTimeTo()
        );
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/package/{id}")
    public ResponseEntity<Void> deleteFromPackage(@PathVariable Long id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}