package org.soa.administratorService.service;

import org.soa.administratorService.repository.CompanyPackageRepository;
import org.soa.administratorService.repository.CompanyServiceRepository;
import org.soa.administratorService.repository.BillingRepository;
import org.soa.administratorService.vao.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private CompanyServiceRepository companyServiceRepository;

    @Autowired
    private CompanyPackageRepository companyPackageRepository;

    public Billing create(float amount, Date date, Long idCompanyService, Long idCompanyPackage) {
        Billing billing = new Billing();
        companyPackageRepository.findById(idCompanyPackage).ifPresent(billing::setCompanyPackage);
        companyServiceRepository.findById(idCompanyService).ifPresent(billing::setCompanyService);
        billing.setAmount(amount);
        billing.setDate(date);
        return billingRepository.save(billing);
    }

    public Billing update(Long id, float amount, Date date, Long idCompanyService, Long idCompanyPackage) {
        Billing billing = billingRepository.findById(id).orElseThrow();
        billing.setAmount(amount);
        billing.setDate(date);
        companyPackageRepository.findById(idCompanyPackage).ifPresent(billing::setCompanyPackage);
        companyServiceRepository.findById(idCompanyService).ifPresent(billing::setCompanyService);
        return billingRepository.save(billing);
    }

    public void delete(Long id) {
        billingRepository.deleteById(id);
    }

    public Billing get(Long id) {
        return billingRepository.findById(id).orElseThrow();
    }

    public List<Billing> getAll() {
        return billingRepository.findAll();
    }
}