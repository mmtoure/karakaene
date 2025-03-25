package com.mmt.karakaene.service.extraction;

import com.mmt.karakaene.model.Extraction;
import com.mmt.karakaene.model.Site;
import com.mmt.karakaene.model.Stockage;
import com.mmt.karakaene.model.User;
import com.mmt.karakaene.repository.ExtractionRepository;
import com.mmt.karakaene.repository.SiteRepository;
import com.mmt.karakaene.repository.StockageRepository;
import com.mmt.karakaene.repository.UserRepository;
import com.mmt.karakaene.request.ExtractionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExtractionServiceImpl implements ExtractionService {
    private final ExtractionRepository extractionRepository;
    private final UserRepository userRepository;
    private final SiteRepository siteRepository;
    private final StockageRepository stockageRepository;

    @Override
    public Extraction createExtraction(ExtractionRequest request) throws Exception {
        System.out.println("Extraction"+request);
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isEmpty()){
            throw new Exception(" User not found");
        }
        Optional<Site> site = siteRepository.findById(request.getSiteId());
        if(site.isEmpty()){
            throw new Exception(" Site not found");
        }

        //Mise a jour le stock du site apres chaque extraction
        Site currentSite = site.get();
        currentSite.setStockActuel(currentSite.getStockActuel()+ request.getQuantity());
        siteRepository.save(currentSite);

        Extraction newExtraction = new Extraction();
        newExtraction.setUser(user.get());
        newExtraction.setSite(site.get());
        newExtraction.setQuantity(request.getQuantity());
        newExtraction.setDate_extraction(request.getDate_extraction());
        return extractionRepository.save(newExtraction);
    }

    @Override
    public Extraction getExtractionById(Long id) throws Exception {
        Optional<Extraction> optionalExtraction = extractionRepository.findById(id);
        if (optionalExtraction.isPresent()){
            return optionalExtraction.get();
        }
        throw new Exception("Extraction not found");
    }

    @Override
    public Extraction updateExtraction(Extraction extraction, Long id) throws Exception {
        Optional<Extraction> optionalExtraction = extractionRepository.findById(id);
        if(optionalExtraction.isEmpty()){
            throw new Exception("Extraction not found");
        }
        Extraction existingExtraction = optionalExtraction.get();
        existingExtraction.setQuantity(extraction.getQuantity());
        existingExtraction.setDate_extraction(extraction.getDate_extraction());
        return extractionRepository.save(existingExtraction);
    }

    @Override
    public List<Extraction> listExtraction() {
        return extractionRepository.findAll();
    }
}
