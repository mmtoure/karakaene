package com.mmt.karakaene.controller;

import com.mmt.karakaene.model.Extraction;
import com.mmt.karakaene.request.ExtractionRequest;
import com.mmt.karakaene.service.extraction.ExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ExtractionController {

    private final ExtractionService extractionService;

    @PostMapping("api/extractions")
    public ResponseEntity<Extraction> create(@RequestBody ExtractionRequest extraction) throws Exception {
        Extraction createExtraction = extractionService.createExtraction(extraction);
        return new ResponseEntity<>(createExtraction, HttpStatus.CREATED);
    }

    @GetMapping("api/extractions")
    public ResponseEntity<List<Extraction>> getAllExtractions() throws Exception {
        List<Extraction> extractions = extractionService.listExtraction();
        return new ResponseEntity<>(extractions,HttpStatus.OK);
    }

    @GetMapping("api/extractions/{id}")
    public ResponseEntity<Extraction> getExtractionById(@PathVariable Long id) throws Exception {
        Extraction extraction = extractionService.getExtractionById(id);
        return new ResponseEntity<>(extraction,HttpStatus.OK);
    }



}
