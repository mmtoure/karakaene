package com.mmt.karakaene.service.site;

import com.mmt.karakaene.model.Site;
import com.mmt.karakaene.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class SiteServiceImpl implements SiteService{

    private final SiteRepository siteRepository;
    @Override
    public Site createSite(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public Site getSite(Long id) {
        return siteRepository.findById(id).orElseThrow(()-> new RuntimeException("Site not found"));
    }

    @Override
    public Site updateSite(Site site, Long id) {
       return Optional.ofNullable(getSite(id)).map(oldCategory ->{
           oldCategory.setName(site.getName());
           oldCategory.setAddress(site.getAddress());
           oldCategory.setLocalisation(site.getLocalisation());
           return siteRepository.save(oldCategory);
       }).orElseThrow(()-> new RuntimeException("Site not found"));
    }

    @Override
    public void deleteSite(Long id) {
        siteRepository.findById(id).
                ifPresentOrElse(siteRepository::delete, () ->{
                    throw  new RuntimeException("Site not found");
                }
        );

    }

    @Override
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }
}
