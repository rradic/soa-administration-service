package org.soa.administratorService.api.controller;

import org.soa.administratorService.dto.BillingDTO;
import org.soa.administratorService.service.BillingService;
import org.soa.administratorService.vao.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<Billing> createBilling(@RequestBody BillingDTO billing) {
        Billing createdBilling = billingService.create(
                billing.getAmount(),
                billing.getDate(),
                billing.getCompanyServiceId(),
                billing.getCompanyPackageId()
        );
        return ResponseEntity.ok(createdBilling);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBilling(@PathVariable Long id, @RequestBody BillingDTO billing) {
        Billing updatedBilling = billingService.update(id,
                billing.getAmount(),
                billing.getDate(),
                billing.getCompanyServiceId(),
                billing.getCompanyPackageId()
        );
        return ResponseEntity.ok(updatedBilling);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
        billingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBilling(@PathVariable Long id) {
        Billing billing = billingService.get(id);
        return ResponseEntity.ok(billing);
    }

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBillings() {
        List<Billing> billings = billingService.getAll();
        return ResponseEntity.ok(billings);
    }
}