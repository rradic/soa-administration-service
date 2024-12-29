package org.soa.administratorService.api.controller;

import org.soa.administratorService.dto.CompanyDTO;
import org.soa.administratorService.dto.CompanyPackageDTO;
import org.soa.administratorService.dto.CompanyServiceDTO;
import org.soa.administratorService.service.CompanyPackageService;
import org.soa.administratorService.service.CompanyService;
import org.soa.administratorService.service.CompanyServiceService;
import org.soa.administratorService.vao.Company;
import org.soa.administratorService.vao.CompanyPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyServiceService companyServiceService;
    @Autowired
    private CompanyPackageService companyPackageService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDTO company) {
        Company createdCompany = companyService.create(
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getLocation(),
                company.getEmail(),
                company.getDescription(),
                company.getAccountable()
        );
        return ResponseEntity.ok(createdCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.update(id,
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getLocation(),
                company.getEmail(),
                company.getDescription(),
                company.getAccountable()
        );
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = companyService.get(id);
        return ResponseEntity.ok(company);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAll();
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/service")
    public ResponseEntity<org.soa.administratorService.vao.CompanyService> addService(@RequestBody CompanyServiceDTO companyService) {
        org.soa.administratorService.vao.CompanyService createdCompany = companyServiceService.create(
                companyService.getCompanyId(),
                companyService.getServiceId(),
                companyService.getTimeFrom(),
                companyService.getTimeTo()
        );
        return ResponseEntity.ok(createdCompany);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<org.soa.administratorService.vao.CompanyService> updateService(@PathVariable Long id, @RequestBody CompanyServiceDTO companyService) {
        org.soa.administratorService.vao.CompanyService createdCompany = companyServiceService.update(
                id,
                companyService.getCompanyId(),
                companyService.getServiceId(),
                companyService.getTimeFrom(),
                companyService.getTimeTo()
        );
        return ResponseEntity.ok(createdCompany);
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<Company> deleteService(@PathVariable Long id) {
        companyServiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/service")
    public ResponseEntity<List<org.soa.administratorService.vao.CompanyService>> getServices() {
        List<org.soa.administratorService.vao.CompanyService> companies = companyServiceService.getAll();
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/package")
    public ResponseEntity<CompanyPackage> addPackage(@RequestBody CompanyPackageDTO companyPackageDTO) {
        CompanyPackage createdCompany = companyPackageService.create(
                companyPackageDTO.getCompanyId(),
                companyPackageDTO.getPackageId(),
                companyPackageDTO.getTimeFrom(),
                companyPackageDTO.getTimeTo()
        );
        return ResponseEntity.ok(createdCompany);
    }

    @PutMapping("/package/{id}")
    public ResponseEntity<CompanyPackage> updatePackage(@PathVariable Long id, @RequestBody CompanyPackageDTO companyPackageDTO) {
        CompanyPackage createdCompany = companyPackageService.update(id,  companyPackageDTO.getCompanyId(),
                companyPackageDTO.getPackageId(),
                companyPackageDTO.getTimeFrom(),
                companyPackageDTO.getTimeTo()
        );
        return ResponseEntity.ok(createdCompany);
    }

    @DeleteMapping("/package/{id}")
    public ResponseEntity<Company> deletePackage(@PathVariable Long id) {
        companyPackageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/package")
    public ResponseEntity<List<CompanyPackage>> getPackages() {
        List<CompanyPackage> companies = companyPackageService.getAll();
        return ResponseEntity.ok(companies);
    }


}