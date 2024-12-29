package org.soa.administratorService.service;

import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.repository.PriceRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.vao.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PackageRepository packageRepository;

    public Price create(float amount, Date timeFrom, Date timeTo, Long idService, Long idPackage) {
        Price price = new Price();
        packageRepository.findById(idPackage).ifPresent(price::setPackage);
        serviceRepository.findById(idService).ifPresent(price::setService);
        price.setValue(amount);
        price.setTimeFrom(timeFrom);
        price.setTimeFrom(timeTo);
        price.setValueWithTax((float) (amount + amount * 0.2));
        return priceRepository.save(price);
    }

    public Price update(Long id, float amount, Date timeFrom, Date timeTo, Long idService, Long idPackage) {
        Price price = priceRepository.findById(id).orElseThrow(() -> new RuntimeException("Price not found"));
        packageRepository.findById(idPackage).ifPresent(price::setPackage);
        serviceRepository.findById(idService).ifPresent(price::setService);
        price.setValue(amount);
        price.setTimeFrom(timeFrom);
        price.setTimeFrom(timeTo);
        price.setValueWithTax((float) (amount + amount * 0.2));
        return priceRepository.save(price);
    }

    public void delete(Long id) {
        priceRepository.deleteById(id);
    }

    public Price get(Long id) {
        return priceRepository.findById(id).orElseThrow(() -> new RuntimeException("Price not found"));
    }

    public List<Price> getAll() {
        return priceRepository.findAll();
    }
}