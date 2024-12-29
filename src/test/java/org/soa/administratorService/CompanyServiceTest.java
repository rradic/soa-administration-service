package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.CompanyRepository;
import org.soa.administratorService.service.CompanyService;
import org.soa.administratorService.vao.Company;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCompany() {
        Company company = new Company();
        company.setName("Test Company");
        company.setAddress("123 Test St");
        company.setPhoneNumber("1234567890");
        company.setLocation("Test City");
        company.setEmail("test@example.com");
        company.setDescription("Test Description");
        company.setAccountable("Test Accountable");

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company result = companyService.create(
                "Test Company",
                "123 Test St",
                "1234567890",
                "Test City",
                "test@example.com",
                "Test Description",
                "Test Accountable"
        );

        assertNotNull(result);
        assertEquals("Test Company", result.getName());
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void updateCompany() {
        Long id = 1L;
        Company company = new Company();
        company.setId(id);
        company.setName("Updated Company");

        when(companyRepository.findById(id)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company result = companyService.update(
                id,
                "Updated Company",
                "123 Updated St",
                "0987654321",
                "Updated City",
                "updated@example.com",
                "Updated Description",
                "Updated Accountable"
        );

        assertNotNull(result);
        assertEquals("Updated Company", result.getName());
        verify(companyRepository).findById(id);
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void deleteCompany() {
        Long id = 1L;

        doNothing().when(companyRepository).deleteById(id);

        companyService.delete(id);

        verify(companyRepository).deleteById(id);
    }

    @Test
    void getCompany() {
        Long id = 1L;
        Company company = new Company();
        company.setId(id);

        when(companyRepository.findById(id)).thenReturn(Optional.of(company));

        Company result = companyService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(companyRepository).findById(id);
    }

    @Test
    void getAllCompanies() {
        when(companyRepository.findAll()).thenReturn(List.of(new Company(), new Company()));

        List<Company> result = companyService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(companyRepository).findAll();
    }
}