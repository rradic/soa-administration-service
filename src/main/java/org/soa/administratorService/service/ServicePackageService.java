package org.soa.administratorService.service;

import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.repository.ServicePackageRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.vao.ServicePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServicePackageService {

    @Autowired
    private ServicePackageRepository servicePackageRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PackageRepository packageRepository;

    public ServicePackage create(Long idService, Long idPackage, Date timeFrom, Date timeTo) {
        ServicePackage servicePackage = new ServicePackage();
        servicePackage.setService(serviceRepository.findById(idService).orElseThrow());
        servicePackage.setaPackage(packageRepository.findById(idPackage).orElseThrow());
        servicePackage.setTimeFrom(timeFrom);
        servicePackage.setTimeTo(timeTo);
        return servicePackageRepository.save(servicePackage);
    }

    public ServicePackage update(Long id, Long idService, Long idPackage, Date timeFrom, Date timeTo) {
        ServicePackage servicePackage = servicePackageRepository.findById(id).orElseThrow(() -> new RuntimeException("ServicePackage not found"));
        serviceRepository.findById(idService).ifPresent(servicePackage::setService);
        packageRepository.findById(idPackage).ifPresent(servicePackage::setaPackage);
        servicePackage.setTimeFrom(timeFrom);
        servicePackage.setTimeTo(timeTo);
        return servicePackageRepository.save(servicePackage);
    }

    public void delete(Long id) {
        servicePackageRepository.deleteById(id);
    }

    public ServicePackage get(Long id) {
        return servicePackageRepository.findById(id).orElseThrow(() -> new RuntimeException("ServicePackage not found"));
    }

    public List<ServicePackage> getServicesByPackage(Long id) {
        return servicePackageRepository.findByServiceId(id);
    }

    public List<ServicePackage> getPackagesByService(Long id) {
        return servicePackageRepository.findByPackageId(id);
    }

    public List<ServicePackage> getAll() {
        return servicePackageRepository.findAll();
    }
}