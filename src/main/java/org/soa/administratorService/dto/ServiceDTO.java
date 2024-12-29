package org.soa.administratorService.dto;

import org.soa.administratorService.vao.Duration;

public class ServiceDTO {

    private String name;
    private String description;
    private int duration;
    private Duration durationPeriod;
    private Long idServiceCategory;

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

    public Long getIdServiceCategory() {
        return idServiceCategory;
    }

    public void setIdServiceCategory(Long idServiceCategory) {
        this.idServiceCategory = idServiceCategory;
    }
}
