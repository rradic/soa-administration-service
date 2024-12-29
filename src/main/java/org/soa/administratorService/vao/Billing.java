package org.soa.administratorService.vao;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "billing")
public class Billing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idCompanyService",  referencedColumnName = "id")
    private CompanyService companyService;

    @ManyToOne
    @JoinColumn(name = "idCompanyPackage",  referencedColumnName = "id")
    private CompanyPackage companyPackage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public CompanyPackage getCompanyPackage() {
        return companyPackage;
    }

    public void setCompanyPackage(CompanyPackage companyPackage) {
        this.companyPackage = companyPackage;
    }
}
