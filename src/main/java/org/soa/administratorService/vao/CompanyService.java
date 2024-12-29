package org.soa.administratorService.vao;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "company_service")
public class CompanyService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCompany",  referencedColumnName = "id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idService",  referencedColumnName = "id")
    private ServiceVao service;

    @Column(name = "timeFrom")
    private Date timeFrom;

    @Column(name = "timeTo")
    private Date timeTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ServiceVao getService() {
        return service;
    }

    public void setService(ServiceVao service) {
        this.service = service;
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
}
