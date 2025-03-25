package com.mmt.karakaene.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double stockActuel;
    private String address;
    private String localisation;
    private LocalDateTime date_mis_a_jour;

    @OneToOne(mappedBy = "site")
    @JsonIgnore
    private Stockage stockage;

    @JsonIgnore
    @OneToMany(mappedBy = "site", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Extraction> extractions;


}
