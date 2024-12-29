package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.CompanyPackageRepository;
import org.soa.administratorService.repository.CompanyRepository;
import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.service.CompanyPackageService;
import org.soa.administratorService.vao.Company;
import org.soa.administratorService.vao.CompanyPackage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyPackageServiceTest {

    @Mock
    private CompanyPackageRepository companyPackageRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private CompanyPackageService companyPackageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCompanyPackage() {
        Long companyId = 1L;
        Long packageId = 1L;
        Date timeFrom = new Date();
        Date timeTo = new Date();

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new Company()));
        when(packageRepository.findById(packageId)).thenReturn(Optional.of(new org.soa.administratorService.vao.Package()));
        when(companyPackageRepository.save(any(CompanyPackage.class))).thenReturn(new CompanyPackage());

        CompanyPackage result = companyPackageService.create(companyId, packageId, timeFrom, timeTo);

        assertNotNull(result);
        verify(companyRepository).findById(companyId);
        verify(packageRepository).findById(packageId);
        verify(companyPackageRepository).save(any(CompanyPackage.class));
    }

    @Test
    void updateCompanyPackage() {
        Long id = 1L;
        Long companyId = 1L;
        Long packageId = 1L;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        CompanyPackage existingCompanyPackage = new CompanyPackage();

        when(companyPackageRepository.findById(id)).thenReturn(Optional.of(existingCompanyPackage));
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new Company()));
        when(packageRepository.findById(packageId)).thenReturn(Optional.of(new org.soa.administratorService.vao.Package()));
        when(companyPackageRepository.save(any(CompanyPackage.class))).thenReturn(existingCompanyPackage);

        CompanyPackage result = companyPackageService.update(id, companyId, packageId, timeFrom, timeTo);

        assertNotNull(result);
        verify(companyPackageRepository).findById(id);
        verify(companyRepository).findById(companyId);
        verify(packageRepository).findById(packageId);
        verify(companyPackageRepository).save(existingCompanyPackage);
    }

    @Test
    void deleteCompanyPackage() {
        Long id = 1L;

        doNothing().when(companyPackageRepository).deleteById(id);

        companyPackageService.delete(id);

        verify(companyPackageRepository).deleteById(id);
    }

    @Test
    void getCompanyPackage() {
        Long id = 1L;
        CompanyPackage companyPackage = new CompanyPackage();

        when(companyPackageRepository.findById(id)).thenReturn(Optional.of(companyPackage));

        CompanyPackage result = companyPackageService.get(id);

        assertNotNull(result);
        verify(companyPackageRepository).findById(id);
    }

    @Test
    void getAllCompanyPackages() {
        when(companyPackageRepository.findAll()).thenReturn(List.of(new CompanyPackage(), new CompanyPackage()));

        List<CompanyPackage> result = companyPackageService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(companyPackageRepository).findAll();
    }
}