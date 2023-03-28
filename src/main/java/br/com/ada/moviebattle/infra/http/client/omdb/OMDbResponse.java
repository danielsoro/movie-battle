package br.com.ada.moviebattle.infra.http.client.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OMDbResponse {

    @JsonProperty("imdbID")
    private String id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("imdbRating")
    private BigDecimal rating;

    @JsonProperty("imdbVotes")
    private BigDecimal votes;
}
