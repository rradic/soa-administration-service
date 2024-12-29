package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.repository.ServicePackageRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.service.ServicePackageService;
import org.soa.administratorService.vao.ServicePackage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicePackageServiceTest {

    @Mock
    private ServicePackageRepository servicePackageRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private ServicePackageService servicePackageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createServicePackage() {
        Long idService = 1L;
        Long idPackage = 1L;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        ServicePackage servicePackage = new ServicePackage();

        when(serviceRepository.findById(idService)).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceVao()));
        when(packageRepository.findById(idPackage)).thenReturn(Optional.of(new org.soa.administratorService.vao.Package()));
        when(servicePackageRepository.save(any(ServicePackage.class))).thenReturn(servicePackage);

        ServicePackage result = servicePackageService.create(idService, idPackage, timeFrom, timeTo);

        assertNotNull(result);
        verify(serviceRepository).findById(idService);
        verify(packageRepository).findById(idPackage);
        verify(servicePackageRepository).save(any(ServicePackage.class));
    }

    @Test
    void updateServicePackage() {
        Long id = 1L;
        Long idService = 1L;
        Long idPackage = 1L;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        ServicePackage existingServicePackage = new ServicePackage();

        when(servicePackageRepository.findById(id)).thenReturn(Optional.of(existingServicePackage));
        when(serviceRepository.findById(idService)).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceVao()));
        when(packageRepository.findById(idPackage)).thenReturn(Optional.of(new org.soa.administratorService.vao.Package()));
        when(servicePackageRepository.save(any(ServicePackage.class))).thenReturn(existingServicePackage);

        ServicePackage result = servicePackageService.update(id, idService, idPackage, timeFrom, timeTo);

        assertNotNull(result);
        verify(servicePackageRepository).findById(id);
        verify(serviceRepository).findById(idService);
        verify(packageRepository).findById(idPackage);
        verify(servicePackageRepository).save(existingServicePackage);
    }

    @Test
    void deleteServicePackage() {
        Long id = 1L;

        doNothing().when(servicePackageRepository).deleteById(id);

        servicePackageService.delete(id);

        verify(servicePackageRepository).deleteById(id);
    }

    @Test
    void getServicePackage() {
        Long id = 1L;
        ServicePackage servicePackage = new ServicePackage();

        when(servicePackageRepository.findById(id)).thenReturn(Optional.of(servicePackage));

        ServicePackage result = servicePackageService.get(id);

        assertNotNull(result);
        verify(servicePackageRepository).findById(id);
    }

    @Test
    void getServicesByPackage() {
        Long id = 1L;
        when(servicePackageRepository.findByServiceId(id)).thenReturn(List.of(new ServicePackage(), new ServicePackage()));

        List<ServicePackage> result = servicePackageService.getServicesByPackage(id);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(servicePackageRepository).findByServiceId(id);
    }

    @Test
    void getPackagesByService() {
        Long id = 1L;
        when(servicePackageRepository.findByPackageId(id)).thenReturn(List.of(new ServicePackage(), new ServicePackage()));

        List<ServicePackage> result = servicePackageService.getPackagesByService(id);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(servicePackageRepository).findByPackageId(id);
    }

    @Test
    void getAllServicePackages() {
        when(servicePackageRepository.findAll()).thenReturn(List.of(new ServicePackage(), new ServicePackage()));

        List<ServicePackage> result = servicePackageService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(servicePackageRepository).findAll();
    }
}