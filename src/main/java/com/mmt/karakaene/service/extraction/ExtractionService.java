package com.mmt.karakaene.service.extraction;

import com.mmt.karakaene.model.Extraction;
import com.mmt.karakaene.request.ExtractionRequest;

import java.util.List;


public interface ExtractionService {
    Extraction createExtraction(ExtractionRequest request) throws Exception;
    Extraction getExtractionById(Long id) throws Exception;
    Extraction updateExtraction(Extraction extraction, Long id) throws Exception;
    List<Extraction> listExtraction();
}
