package com.mmt.karakaene.controller;

import com.mmt.karakaene.model.Site;
import com.mmt.karakaene.service.site.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping("api/sites")
    public ResponseEntity<Site> create(@RequestBody Site site){
        Site newSite = siteService.createSite(site);
        return new ResponseEntity<>(newSite, HttpStatus.CREATED);
    }

    @GetMapping("api/sites")
    public ResponseEntity<List<Site>> getAllSites(){
        List<Site> sites = siteService.getAllSites();
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }

    @GetMapping("api/sites/{id}")
    public ResponseEntity<Site> getSite(@PathVariable Long id){
        Site site = siteService.getSite(id);
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    @PutMapping("api/sites/{id}")
    public ResponseEntity<Site> update(@RequestBody Site site, @PathVariable Long id){
        Site existingSite = siteService.updateSite(site,id);
        return new ResponseEntity<>(existingSite, HttpStatus.OK);
    }
}
