package org.soa.administratorService.api.controller;

import org.soa.administratorService.dto.PriceDTO;
import org.soa.administratorService.service.PriceService;
import org.soa.administratorService.vao.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getAllPrices() {
        List<Price> prices = priceService.getAll();
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable Long id) {
        Price price = priceService.get(id);
        return ResponseEntity.ok(price);
    }

    @PostMapping
    public ResponseEntity<Price> createPrice(@RequestBody PriceDTO price) {
        Price createdPrice = priceService.create(price.getValue(), price.getTimeFrom(), price.getTimeTo(), price.getServiceId(), price.getPackageId());
        return ResponseEntity.ok(createdPrice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable Long id, @RequestBody PriceDTO price) {
        Price updatedPrice = priceService.update(
                id,
                price.getValue(),
                price.getTimeFrom(),
                price.getTimeTo(),
                price.getServiceId(),
                price.getPackageId()
        );
        return ResponseEntity.ok(updatedPrice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        priceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}