package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.request.UserRequestDto;
import racingcar.dto.response.GameResultResponseDto;
import racingcar.service.RacingGameService;

import java.util.List;

@Controller
public class WebController {

    private final RacingGameService racingGameService;

    public WebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @GetMapping("/plays")
    @ResponseBody
    public ResponseEntity<List<GameResultResponseDto>> getHistory() {
        return ResponseEntity.ok(racingGameService.getHistory());
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> runRacingGame(@RequestBody final UserRequestDto inputDto) {
        return ResponseEntity.ok(racingGameService.getResult(inputDto));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> hello(final RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> hello(final Exception exception) {
        return ResponseEntity.internalServerError().body("예기치 못한 에러가 발생했습니다.");
    }
}
