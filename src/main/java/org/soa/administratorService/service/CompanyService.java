package org.soa.administratorService.service;

import org.soa.administratorService.repository.CompanyRepository;
import org.soa.administratorService.vao.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company create(
            String name,
            String address,
            String phoneNumber,
            String location,
            String email,
            String description,
            String accountable
    ) {
        Company company = new Company();
        company.setAccountable(accountable);
        company.setDescription(description);
        company.setAddress(address);
        company.setName(name);
        company.setPhoneNumber(phoneNumber);
        company.setLocation(location);
        company.setEmail(email);
        return companyRepository.save(company);
    }

    public Company update(
            Long id,
            String name,
            String address,
            String phoneNumber,
            String location,
            String email,
            String description,
            String accountable
    ) {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setAccountable(accountable);
        company.setDescription(description);
        company.setAddress(address);
        company.setName(name);
        company.setPhoneNumber(phoneNumber);
        company.setLocation(location);
        company.setEmail(email);
        return companyRepository.save(company);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    public Company get(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
