package org.soa.administratorService.vao;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private float value;

    @Column(name = "valueWithTax")
    private float valueWithTax;

    @Column(name = "timeFrom")
    private Date timeFrom;

    @Column(name = "timeTo")
    private Date timeTo;

    @ManyToOne
    @JoinColumn(name = "idService",  referencedColumnName = "id")
    private ServiceVao service;

    @ManyToOne
    @JoinColumn(name = "idPackage",  referencedColumnName = "id")
    private Package Package;

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

    public ServiceVao getService() {
        return service;
    }

    public void setService(ServiceVao service) {
        this.service = service;
    }

    public Package getPackage() {
        return Package;
    }

    public void setPackage(Package aPackage) {
        Package = aPackage;
    }
}
