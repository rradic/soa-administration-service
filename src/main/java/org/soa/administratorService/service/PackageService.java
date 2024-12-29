package org.soa.administratorService.service;

import org.soa.administratorService.repository.PackageRepository;
import org.soa.administratorService.vao.Duration;
import org.soa.administratorService.vao.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public Package create(String name, String description, int duration, Duration durationPeriod) {
        Package pkg = new Package();
        pkg.setName(name);
        pkg.setDuration(duration);
        pkg.setDurationPeriod(durationPeriod);
        pkg.setDescription(description);
        return packageRepository.save(pkg);
    }

    public Package update(Long id, String name, String description, int duration, Duration durationPeriod) {
        Package pkg = packageRepository.findById(id).orElseThrow();
        pkg.setName(name);
        pkg.setDescription(description);
        pkg.setDuration(duration);
        pkg.setDurationPeriod(durationPeriod);
        return packageRepository.save(pkg);
    }

    public void delete(Long id) {
        packageRepository.deleteById(id);
    }

    public Package get(Long id) {
        return packageRepository.findById(id).orElseThrow();
    }

    public List<Package> getAll() {
        return packageRepository.findAll();
    }
}