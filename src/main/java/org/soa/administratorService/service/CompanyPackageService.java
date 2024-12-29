package org.soa.administratorService.service;

import org.soa.administratorService.repository.CompanyPackageRepository;
import org.soa.administratorService.repository.CompanyRepository;
import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.vao.CompanyPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyPackageService {

    @Autowired
    private CompanyPackageRepository companyPackageRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PackageRepository packageRepository;

    public CompanyPackage create(Long idCompany, Long idPackage, Date timeFrom, Date timeTo) {
        CompanyPackage companyPackage = new CompanyPackage();
        packageRepository.findById(idPackage).ifPresent(companyPackage::setaPackage);
        companyRepository.findById(idCompany).ifPresent(companyPackage::setCompany);
        companyPackage.setTimeFrom(timeFrom);
        companyPackage.setTimeTo(timeTo);
        return companyPackageRepository.save(companyPackage);
    }


    public CompanyPackage update(Long id, Long idCompany, Long idPackage, Date timeFrom, Date timeTo) {
        CompanyPackage companyPackage = companyPackageRepository.findById(id).orElseThrow();
        packageRepository.findById(idPackage).ifPresent(companyPackage::setaPackage);
        companyRepository.findById(idCompany).ifPresent(companyPackage::setCompany);
        companyPackage.setTimeFrom(timeFrom);
        companyPackage.setTimeTo(timeTo);
        return companyPackageRepository.save(companyPackage);
    }

    public void delete(Long id) {
        companyPackageRepository.deleteById(id);
    }

    public CompanyPackage get(Long id) {
        return companyPackageRepository.findById(id).orElseThrow();
    }

    public List<CompanyPackage> getAll() {
        return companyPackageRepository.findAll();
    }
}