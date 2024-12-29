package org.soa.administratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.repository.PriceRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.service.PriceService;
import org.soa.administratorService.vao.Price;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPrice() {
        float amount = 100.0f;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        Long idService = 1L;
        Long idPackage = 1L;
        Price price = new Price();

        when(packageRepository.findById(idPackage)).thenReturn(Optional.of(new org.soa.administratorService.vao.Package()));
        when(serviceRepository.findById(idService)).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceVao()));
        when(priceRepository.save(any(Price.class))).thenReturn(price);

        Price result = priceService.create(amount, timeFrom, timeTo, idService, idPackage);

        assertNotNull(result);
        verify(packageRepository).findById(idPackage);
        verify(serviceRepository).findById(idService);
        verify(priceRepository).save(any(Price.class));
    }

    @Test
    void updatePrice() {
        Long id = 1L;
        float amount = 100.0f;
        Date timeFrom = new Date();
        Date timeTo = new Date();
        Long idService = 1L;
        Long idPackage = 1L;
        Price existingPrice = new Price();

        when(priceRepository.findById(id)).thenReturn(Optional.of(existingPrice));
        when(packageRepository.findById(idPackage)).thenReturn(Optional.of(new org.soa.administratorService.vao.Package()));
        when(serviceRepository.findById(idService)).thenReturn(Optional.of(new org.soa.administratorService.vao.ServiceVao()));
        when(priceRepository.save(any(Price.class))).thenReturn(existingPrice);

        Price result = priceService.update(id, amount, timeFrom, timeTo, idService, idPackage);

        assertNotNull(result);
        verify(priceRepository).findById(id);
        verify(packageRepository).findById(idPackage);
        verify(serviceRepository).findById(idService);
        verify(priceRepository).save(existingPrice);
    }

    @Test
    void deletePrice() {
        Long id = 1L;

        doNothing().when(priceRepository).deleteById(id);

        priceService.delete(id);

        verify(priceRepository).deleteById(id);
    }

    @Test
    void getPrice() {
        Long id = 1L;
        Price price = new Price();

        when(priceRepository.findById(id)).thenReturn(Optional.of(price));

        Price result = priceService.get(id);

        assertNotNull(result);
        verify(priceRepository).findById(id);
    }

    @Test
    void getAllPrices() {
        when(priceRepository.findAll()).thenReturn(List.of(new Price(), new Price()));

        List<Price> result = priceService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(priceRepository).findAll();
    }
}