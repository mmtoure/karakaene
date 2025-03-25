package com.mmt.karakaene.service.site;

import com.mmt.karakaene.model.Site;

import java.util.List;

public interface SiteService {
    Site createSite(Site site);
    Site getSite(Long id);
    Site updateSite(Site site, Long id);
    void deleteSite(Long id);
    List<Site> getAllSites();
}
