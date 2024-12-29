package org.soa.administratorService.vao;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class ServiceVao {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idServiceCategory",  referencedColumnName = "id")
    private ServiceCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "durationPeriod")
    private Duration durationPeriod;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Duration getDurationPeriod() {
        return durationPeriod;
    }

    public void setDurationPeriod(Duration durationPeriod) {
        this.durationPeriod = durationPeriod;
    }
}
