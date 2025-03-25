package com.mmt.karakaene.request;

import com.mmt.karakaene.model.Site;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockageRequest {
    private Double stock;
    private Long siteId;
}
