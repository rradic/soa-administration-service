package org.soa.administratorService.service;

import org.soa.administratorService.repository.CompanyRepository;
import org.soa.administratorService.repository.CompanyServiceRepository;
import org.soa.administratorService.repository.ServiceRepository;
import org.soa.administratorService.vao.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceService {

    @Autowired
    private CompanyServiceRepository companyServiceRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public CompanyService create(Long idCompany, Long idService, Date timeFrom, Date timeTo) {
        CompanyService companyService = new CompanyService();
        serviceRepository.findById(idService).ifPresent(companyService::setService);
        companyRepository.findById(idCompany).ifPresent(companyService::setCompany);
        companyService.setTimeFrom(timeFrom);
        companyService.setTimeTo(timeTo);
        return companyServiceRepository.save(companyService);
    }

    public CompanyService update(Long idCompanyService, Long idCompany, Long idService, Date timeFrom, Date timeTo) {
        CompanyService companyService =  companyServiceRepository.findById(idCompanyService).orElseThrow();
        serviceRepository.findById(idService).ifPresent(companyService::setService);
        companyRepository.findById(idCompany).ifPresent(companyService::setCompany);
        companyService.setTimeFrom(timeFrom);
        companyService.setTimeTo(timeTo);
        return companyServiceRepository.save(companyService);
    }

    public void delete(Long id) {
        companyServiceRepository.deleteById(id);
    }

    public CompanyService get(Long id) {
        return companyServiceRepository.findById(id).orElseThrow();
    }

    public List<CompanyService> getAll() {
        return companyServiceRepository.findAll();
    }
}
