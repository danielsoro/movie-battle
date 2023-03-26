package br.com.ada.moviebattle.infra.http.client.omdb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "imdb-client", url = "${br.com.ada.moviebattle.omdb.url}")
public interface OMDbClient {

    @GetMapping("/?type=movie&r=json")
    OMDbResponse getMovie(@RequestParam("apikey") String apiKey, @RequestParam("t") String title);

    @GetMapping("/?type=movie&r=json")
    OMBdSearchResponse searchMovies(@RequestParam("apikey") String apiKey, @RequestParam("s") String title);
}
