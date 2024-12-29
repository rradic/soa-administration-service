package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.service.PackageService;
import org.soa.administratorService.vao.Duration;
import org.soa.administratorService.vao.Package;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PackageServiceTest {

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private PackageService packageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPackage() {
        Package pkg = new Package();
        pkg.setName("Test Package");
        pkg.setDescription("Test Description");
        pkg.setDuration(30);
        pkg.setDurationPeriod(Duration.DAY);

        when(packageRepository.save(any(Package.class))).thenReturn(pkg);

        Package result = packageService.create("Test Package", "Test Description", 30, Duration.DAY);

        assertNotNull(result);
        assertEquals("Test Package", result.getName());
        verify(packageRepository).save(any(Package.class));
    }

    @Test
    void updatePackage() {
        Long id = 1L;
        Package pkg = new Package();
        pkg.setId(id);
        pkg.setName("Updated Package");

        when(packageRepository.findById(id)).thenReturn(Optional.of(pkg));
        when(packageRepository.save(any(Package.class))).thenReturn(pkg);

        Package result = packageService.update(id, "Updated Package", "Updated Description", 60, Duration.MONTH);

        assertNotNull(result);
        assertEquals("Updated Package", result.getName());
        verify(packageRepository).findById(id);
        verify(packageRepository).save(any(Package.class));
    }

    @Test
    void deletePackage() {
        Long id = 1L;

        doNothing().when(packageRepository).deleteById(id);

        packageService.delete(id);

        verify(packageRepository).deleteById(id);
    }

    @Test
    void getPackage() {
        Long id = 1L;
        Package pkg = new Package();
        pkg.setId(id);

        when(packageRepository.findById(id)).thenReturn(Optional.of(pkg));

        Package result = packageService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(packageRepository).findById(id);
    }

    @Test
    void getAllPackages() {
        when(packageRepository.findAll()).thenReturn(List.of(new Package(), new Package()));

        List<Package> result = packageService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(packageRepository).findAll();
    }
}