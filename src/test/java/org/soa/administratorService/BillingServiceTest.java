package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.BillingRepository;
import org.soa.administratorService.repository.CompanyPackageRepository;
import org.soa.administratorService.repository.CompanyServiceRepository;
import org.soa.administratorService.service.BillingService;
import org.soa.administratorService.vao.CompanyService;
import org.soa.administratorService.vao.Billing;
import org.soa.administratorService.vao.CompanyPackage;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillingServiceTest {

    @InjectMocks
    private BillingService billingService;

    @Mock
    private BillingRepository billingRepository;

    @Mock
    private CompanyServiceRepository companyServiceRepository;

    @Mock
    private CompanyPackageRepository companyPackageRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        // Arrange
        Billing billing = new Billing();
        when(companyPackageRepository.findById(anyLong())).thenReturn(Optional.of(new CompanyPackage()));
        when(companyServiceRepository.findById(anyLong())).thenReturn(Optional.of(new CompanyService()));
        when(billingRepository.save(any(Billing.class))).thenReturn(billing);

        // Act
        Billing createdBilling = billingService.create(100.0f, new Date(), 1L, 1L);

        // Assert
        assertNotNull(createdBilling);
        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    public void testUpdate() {
        // Arrange
        Billing billing = new Billing();
        when(billingRepository.findById(anyLong())).thenReturn(Optional.of(billing));
        when(companyPackageRepository.findById(anyLong())).thenReturn(Optional.of(new CompanyPackage()));
        when(companyServiceRepository.findById(anyLong())).thenReturn(Optional.of(new CompanyService()));
        when(billingRepository.save(any(Billing.class))).thenReturn(billing);

        // Act
        Billing updatedBilling = billingService.update(1L, 200.0f, new Date(), 1L, 1L);

        // Assert
        assertNotNull(updatedBilling);
        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    public void testDelete() {
        // Act
        billingService.delete(1L);

        // Assert
        verify(billingRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGet() {
        // Arrange
        Billing billing = new Billing();
        when(billingRepository.findById(anyLong())).thenReturn(Optional.of(billing));

        // Act
        Billing foundBilling = billingService.get(1L);

        // Assert
        assertNotNull(foundBilling);
        verify(billingRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAll() {
        // Act
        billingService.getAll();

        // Assert
        verify(billingRepository, times(1)).findAll();
    }
}