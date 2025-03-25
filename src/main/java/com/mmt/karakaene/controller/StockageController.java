package com.mmt.karakaene.controller;

import com.mmt.karakaene.model.Stockage;
import com.mmt.karakaene.request.StockageRequest;
import com.mmt.karakaene.service.stockage.StockageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class StockageController {
    private final StockageService stockageService;

    @PostMapping("api/stockages")
    public ResponseEntity<Stockage> saveStockage(@RequestBody StockageRequest request) throws Exception {
        Stockage newStockage = stockageService.createStockage(request);
        return new ResponseEntity<>(newStockage, HttpStatus.CREATED);
    }

    @GetMapping("api/stockages")
    public ResponseEntity<List<Stockage>> getAllStockage(){
        List<Stockage> listStockages = stockageService.getAllStockage();
        return new ResponseEntity<>(listStockages, HttpStatus.OK);
    }

    @GetMapping("api/stockages/{id}")
    public  ResponseEntity<Stockage> getStockageById(@PathVariable Long id){
        Stockage stockage = stockageService.getStockageById(id);
        return new ResponseEntity<>(stockage, HttpStatus.OK);

    }

    @PutMapping("api/stockages/{id}")
    public  ResponseEntity<Stockage> getStockageById(@RequestBody Stockage stockage,@PathVariable Long id) throws Exception {
        Stockage existingStockage = stockageService.updateStockage(stockage,id);
        return new ResponseEntity<>(existingStockage, HttpStatus.OK);

    }

    @DeleteMapping("api/stockages/{id}")
    public  ResponseEntity<String> deletestockage(@PathVariable Long id){
        stockageService.deleteStockage(id);
        return new ResponseEntity<>("Stockage deleted", HttpStatus.OK);

    }
}
