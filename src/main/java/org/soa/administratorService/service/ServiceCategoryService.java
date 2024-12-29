package org.soa.administratorService.service;

import org.soa.administratorService.repository.ServiceCategoryRepository;
import org.soa.administratorService.vao.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryService {

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public ServiceCategory create(String name, String description) {
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setName(name);
        serviceCategory.setDescription(description);
        return serviceCategoryRepository.save(serviceCategory);
    }

    public ServiceCategory update(Long id, String name, String description) {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("ServiceCategory not found"));
        serviceCategory.setName(name);
        serviceCategory.setDescription(description);
        return serviceCategoryRepository.save(serviceCategory);
    }

    public void delete(Long id) {
        serviceCategoryRepository.deleteById(id);
    }

    public ServiceCategory get(Long id) {
        return serviceCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("ServiceCategory not found"));
    }

    public List<ServiceCategory> getAll() {
        return serviceCategoryRepository.findAll();
    }
}