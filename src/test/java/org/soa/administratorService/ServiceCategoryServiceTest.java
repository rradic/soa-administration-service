package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.ServiceCategoryRepository;
import org.soa.administratorService.service.ServiceCategoryService;
import org.soa.administratorService.vao.ServiceCategory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceCategoryServiceTest {

    @Mock
    private ServiceCategoryRepository serviceCategoryRepository;

    @InjectMocks
    private ServiceCategoryService serviceCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createServiceCategory() {
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setName("Test Category");
        serviceCategory.setDescription("Test Description");

        when(serviceCategoryRepository.save(any(ServiceCategory.class))).thenReturn(serviceCategory);

        ServiceCategory result = serviceCategoryService.create("Test Category", "Test Description");

        assertNotNull(result);
        assertEquals("Test Category", result.getName());
        verify(serviceCategoryRepository).save(any(ServiceCategory.class));
    }

    @Test
    void updateServiceCategory() {
        Long id = 1L;
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setId(id);
        serviceCategory.setName("Updated Category");

        when(serviceCategoryRepository.findById(id)).thenReturn(Optional.of(serviceCategory));
        when(serviceCategoryRepository.save(any(ServiceCategory.class))).thenReturn(serviceCategory);

        ServiceCategory result = serviceCategoryService.update(id, "Updated Category", "Updated Description");

        assertNotNull(result);
        assertEquals("Updated Category", result.getName());
        verify(serviceCategoryRepository).findById(id);
        verify(serviceCategoryRepository).save(any(ServiceCategory.class));
    }

    @Test
    void deleteServiceCategory() {
        Long id = 1L;

        doNothing().when(serviceCategoryRepository).deleteById(id);

        serviceCategoryService.delete(id);

        verify(serviceCategoryRepository).deleteById(id);
    }

    @Test
    void getServiceCategory() {
        Long id = 1L;
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setId(id);

        when(serviceCategoryRepository.findById(id)).thenReturn(Optional.of(serviceCategory));

        ServiceCategory result = serviceCategoryService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(serviceCategoryRepository).findById(id);
    }

    @Test
    void getAllServiceCategories() {
        when(serviceCategoryRepository.findAll()).thenReturn(List.of(new ServiceCategory(), new ServiceCategory()));

        List<ServiceCategory> result = serviceCategoryService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(serviceCategoryRepository).findAll();
    }
}