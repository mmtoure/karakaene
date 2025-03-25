package com.mmt.karakaene.service.stockage;

import com.mmt.karakaene.model.Stockage;
import com.mmt.karakaene.request.StockageRequest;

import java.util.List;

public interface StockageService {
    Stockage createStockage(StockageRequest request) throws Exception;
    Stockage getStockageById(Long id);
    Stockage updateStockage(Stockage stockage, Long id) throws Exception;
    void deleteStockage(Long id);
    List<Stockage> getAllStockage();
}
