package org.soa.administratorService.service;

import org.soa.administratorService.repository.ServiceCategoryRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.vao.Duration;
import org.soa.administratorService.vao.ServiceVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    private ServiceCategoryRepository serviceCategoryRepository;

    public ServiceVao create(String name, String description, int duration, Duration durationPeriod, Long idServiceCategory) {
        ServiceVao service = new ServiceVao();
        service.setName(name);
        service.setDescription(description);
        service.setDuration(duration);
        service.setDurationPeriod(durationPeriod);
        service.setCategory(serviceCategoryRepository.findById(idServiceCategory).orElseThrow());
        return serviceRepository.save(service);
    }

    public ServiceVao update(Long id, String name, String description, int duration, Duration durationPeriod, Long idServiceCategory) {
        ServiceVao service = serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
        service.setName(name);
        service.setDescription(description);
        service.setDuration(duration);
        service.setDurationPeriod(durationPeriod);
        service.setCategory(serviceCategoryRepository.findById(idServiceCategory).orElseThrow());
        return serviceRepository.save(service);
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    public ServiceVao get(Long id) {
        return serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public List<ServiceVao> getAll() {
        return serviceRepository.findAll();
    }
}