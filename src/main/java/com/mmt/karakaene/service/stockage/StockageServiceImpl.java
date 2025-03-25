package com.mmt.karakaene.service.stockage;

import com.mmt.karakaene.model.Site;
import com.mmt.karakaene.model.Stockage;
import com.mmt.karakaene.repository.SiteRepository;
import com.mmt.karakaene.repository.StockageRepository;
import com.mmt.karakaene.request.StockageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StockageServiceImpl implements StockageService{

    private final StockageRepository stockageRepository;
    private final SiteRepository siteRepository;
    @Override
    public Stockage createStockage(StockageRequest request) throws Exception {
        Optional<Site> site = siteRepository.findById(request.getSiteId());
        if(site.isEmpty()){
            throw  new Exception(" Site not found");
        }
        Stockage newStockage = new Stockage();
        newStockage.setSite(site.get());
        newStockage.setStock(request.getStock());
        return stockageRepository.save(newStockage);
    }

    @Override
    public Stockage getStockageById(Long id) {
        Optional<Stockage> opt = stockageRepository.findById(id);
        if(opt.isEmpty()){
            throw new RuntimeException("Stockage not found");
        }
        return opt.get();
    }

    @Override
    public Stockage updateStockage(Stockage stockage, Long id) throws Exception {
        Optional<Stockage> opt = stockageRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Stockage not found");
        }
        Stockage existingStockage = opt.get();
        existingStockage.setStock(stockage.getStock());
        existingStockage.setSite(stockage.getSite());
        stockageRepository.save(existingStockage);
        return  existingStockage;
    }

    @Override
    public void deleteStockage(Long id) {
        Optional<Stockage> opt = stockageRepository.findById(id);
        if(opt.isEmpty()){
            throw new RuntimeException("Stockage not found");
        }
        stockageRepository.delete(opt.get());
    }

    @Override
    public List<Stockage> getAllStockage() {
        return stockageRepository.findAll();
    }
}
