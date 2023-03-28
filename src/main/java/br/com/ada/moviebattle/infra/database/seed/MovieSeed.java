package br.com.ada.moviebattle.infra.database.seed;

import br.com.ada.moviebattle.infra.database.entities.Movie;
import br.com.ada.moviebattle.infra.database.repositories.MovieRepository;
import br.com.ada.moviebattle.infra.http.client.omdb.OMDbClient;
import br.com.ada.moviebattle.infra.http.client.omdb.OMDbResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieSeed {

    private final String apiKey;
    private final boolean seedEnabled;
    private final OMDbClient omDbClient;
    private final MovieRepository movieRepository;

    public MovieSeed(@Value("${br.com.ada.moviebattle.omdb.key}") final String apiKey,
                     @Value("${br.com.ada.moviebattle.omdb.seed:false}") final Boolean seedEnabled,
                     @Autowired final OMDbClient omDbClient,
                     @Autowired final MovieRepository movieRepository) {
        this.apiKey = apiKey;
        this.seedEnabled = seedEnabled;
        this.omDbClient = omDbClient;
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void seedMovies() {
        if (!seedEnabled) {
            return;
        }

        omDbClient.searchMovies(apiKey, "Batman")
                .getSearch().forEach(omDbResponse ->
                        persistMovie(omDbClient.getMovieById(apiKey, omDbResponse.getId()))
                );

        omDbClient.searchMovies(apiKey, "John Wick")
                .getSearch().forEach(omDbResponse ->
                        persistMovie(omDbClient.getMovieById(apiKey, omDbResponse.getId()))
                );

        omDbClient.searchMovies(apiKey, "Marvel")
                .getSearch().forEach(omDbResponse ->
                        persistMovie(omDbClient.getMovieById(apiKey, omDbResponse.getId()))
                );
    }

    private Movie persistMovie(OMDbResponse omDbResponse) {
        return movieRepository.save(Movie.builder()
                .title(omDbResponse.getTitle())
                .rate(omDbResponse.getRating())
                .build());
    }
}
