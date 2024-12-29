package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.ServiceCategoryRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.service.ServiceService;
import org.soa.administratorService.vao.Duration;
import org.soa.administratorService.vao.ServiceVao;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceCategoryRepository serviceCategoryRepository;

    @InjectMocks
    private ServiceService serviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createService() {
        ServiceVao service = new ServiceVao();
        service.setName("Test Service");
        service.setDescription("Test Description");
        service.setDuration(30);
        service.setDurationPeriod(Duration.DAY);

        when(serviceCategoryRepository.findById(anyLong())).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceCategory()));
        when(serviceRepository.save(any(ServiceVao.class))).thenReturn(service);

        ServiceVao result = serviceService.create("Test Service", "Test Description", 30, Duration.DAY, 1L);

        assertNotNull(result);
        assertEquals("Test Service", result.getName());
        verify(serviceCategoryRepository).findById(anyLong());
        verify(serviceRepository).save(any(ServiceVao.class));
    }

    @Test
    void updateService() {
        Long id = 1L;
        ServiceVao service = new ServiceVao();
        service.setId(id);
        service.setName("Updated Service");

        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));
        when(serviceCategoryRepository.findById(anyLong())).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceCategory()));
        when(serviceRepository.save(any(ServiceVao.class))).thenReturn(service);

        ServiceVao result = serviceService.update(id, "Updated Service", "Updated Description", 60, Duration.MONTH, 1L);

        assertNotNull(result);
        assertEquals("Updated Service", result.getName());
        verify(serviceRepository).findById(id);
        verify(serviceCategoryRepository).findById(anyLong());
        verify(serviceRepository).save(any(ServiceVao.class));
    }

    @Test
    void deleteService() {
        Long id = 1L;

        doNothing().when(serviceRepository).deleteById(id);

        serviceService.delete(id);

        verify(serviceRepository).deleteById(id);
    }

    @Test
    void getService() {
        Long id = 1L;
        ServiceVao service = new ServiceVao();
        service.setId(id);

        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));

        ServiceVao result = serviceService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(serviceRepository).findById(id);
    }

    @Test
    void getAllServices() {
        when(serviceRepository.findAll()).thenReturn(List.of(new ServiceVao(), new ServiceVao()));

        List<ServiceVao> result = serviceService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(serviceRepository).findAll();
    }
}