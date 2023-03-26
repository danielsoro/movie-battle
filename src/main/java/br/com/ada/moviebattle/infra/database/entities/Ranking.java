package br.com.ada.moviebattle.infra.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "points")
    private int points;

    @Version
    @Column(name = "version")
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
