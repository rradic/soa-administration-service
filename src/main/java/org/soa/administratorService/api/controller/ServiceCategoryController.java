package org.soa.administratorService.api.controller;

import org.soa.administratorService.dto.ServiceCategoryDTO;
import org.soa.administratorService.vao.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-categories")
public class ServiceCategoryController {

    @Autowired
    private org.soa.administratorService.service.ServiceCategoryService serviceCategoryService;

    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllServiceCategories() {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getAll();
        return ResponseEntity.ok(serviceCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getServiceCategoryById(@PathVariable Long id) {
        ServiceCategory serviceCategory = serviceCategoryService.get(id);
        return ResponseEntity.ok(serviceCategory);
    }

    @PostMapping
    public ResponseEntity<ServiceCategory> createServiceCategory(@RequestBody ServiceCategoryDTO serviceCategory) {
        ServiceCategory createdServiceCategory = serviceCategoryService.create(serviceCategory.getName(), serviceCategory.getDescription());
        return ResponseEntity.ok(createdServiceCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategory> updateServiceCategory(@PathVariable Long id, @RequestBody ServiceCategoryDTO serviceCategory) {
        ServiceCategory updatedServiceCategory = serviceCategoryService.update(
                id,
                serviceCategory.getName(),
                serviceCategory.getDescription()
        );
        return ResponseEntity.ok(updatedServiceCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable Long id) {
        serviceCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}