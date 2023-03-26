package br.com.ada.moviebattle.infra.http.client.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class OMBdSearchResponse {
    @JsonProperty("Search")
    Set<OMDbResponse> search;
}
