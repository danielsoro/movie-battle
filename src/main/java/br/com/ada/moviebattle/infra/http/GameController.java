package br.com.ada.moviebattle.infra.http;

import br.com.ada.moviebattle.business.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/battle")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start")
    public void start(Principal principal) {
        try {
            gameService.start(principal.getName());
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stop")
    public void stop(Principal principal) {
        try {
            gameService.stop(principal.getName());
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
