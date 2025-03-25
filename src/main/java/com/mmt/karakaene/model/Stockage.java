package com.mmt.karakaene.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Stockage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double stock;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site site;
}
