package org.soa.administratorService.dto;

import java.util.Date;

public class PriceDTO {

    private Long id;
    private float value;
    private float valueWithTax;
    private Date timeFrom;
    private Date timeTo;
    private Long serviceId;
    private Long packageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValueWithTax() {
        return valueWithTax;
    }

    public void setValueWithTax(float valueWithTax) {
        this.valueWithTax = valueWithTax;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    // Getters and Setters
}