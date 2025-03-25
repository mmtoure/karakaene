package com.mmt.karakaene.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExtractionRequest {
    private Long id;
    private Double quantity;
    private LocalDate date_extraction;
    private Long userId;
    private Long siteId;
}
