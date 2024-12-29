package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.CompanyRepository;
import org.soa.administratorService.repository.CompanyServiceRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.service.CompanyServiceService;
import org.soa.administratorService.vao.CompanyService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceServiceTest {

    @Mock
    private CompanyServiceRepository companyServiceRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private CompanyServiceService companyServiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCompanyService() {
        Long companyId = 1L;
        Long serviceId = 1L;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        CompanyService companyService = new CompanyService();

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new org.soa.administratorService.vao.Company()));
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceVao()));
        when(companyServiceRepository.save(any(CompanyService.class))).thenReturn(companyService);

        CompanyService result = companyServiceService.create(companyId, serviceId, timeFrom, timeTo);

        assertNotNull(result);
        verify(companyRepository).findById(companyId);
        verify(serviceRepository).findById(serviceId);
        verify(companyServiceRepository).save(any(CompanyService.class));
    }

    @Test
    void updateCompanyService() {
        Long id = 1L;
        Long companyId = 1L;
        Long serviceId = 1L;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        CompanyService existingCompanyService = new CompanyService();

        when(companyServiceRepository.findById(id)).thenReturn(Optional.of(existingCompanyService));
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new org.soa.administratorService.vao.Company()));
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceVao()));
        when(companyServiceRepository.save(any(CompanyService.class))).thenReturn(existingCompanyService);

        CompanyService result = companyServiceService.update(id, companyId, serviceId, timeFrom, timeTo);

        assertNotNull(result);
        verify(companyServiceRepository).findById(id);
        verify(companyRepository).findById(companyId);
        verify(serviceRepository).findById(serviceId);
        verify(companyServiceRepository).save(existingCompanyService);
    }

    @Test
    void deleteCompanyService() {
        Long id = 1L;

        doNothing().when(companyServiceRepository).deleteById(id);

        companyServiceService.delete(id);

        verify(companyServiceRepository).deleteById(id);
    }

    @Test
    void getCompanyService() {
        Long id = 1L;
        CompanyService companyService = new CompanyService();

        when(companyServiceRepository.findById(id)).thenReturn(Optional.of(companyService));

        CompanyService result = companyServiceService.get(id);

        assertNotNull(result);
        verify(companyServiceRepository).findById(id);
    }

    @Test
    void getAllCompanyServices() {
        when(companyServiceRepository.findAll()).thenReturn(List.of(new CompanyService(), new CompanyService()));

        List<CompanyService> result = companyServiceService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(companyServiceRepository).findAll();
    }
}