package org.soa.administratorService.dto;

import java.util.Date;

public class BillingDTO {

    private Long id;
    private float amount;
    private Date date;
    private Long companyServiceId;
    private Long companyPackageId;

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

    public Long getCompanyServiceId() {
        return companyServiceId;
    }

    public void setCompanyServiceId(Long companyServiceId) {
        this.companyServiceId = companyServiceId;
    }

    public Long getCompanyPackageId() {
        return companyPackageId;
    }

    public void setCompanyPackageId(Long companyPackageId) {
        this.companyPackageId = companyPackageId;
    }
}
