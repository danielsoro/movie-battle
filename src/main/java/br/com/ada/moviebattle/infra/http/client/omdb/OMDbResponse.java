package br.com.ada.moviebattle.infra.http.client.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OMDbResponse {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("imbdRating")
    private BigDecimal rating;
}
