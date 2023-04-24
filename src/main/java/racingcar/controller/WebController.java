package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.UserRequestDto;
import racingcar.dto.response.GameResultResponseDto;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebController {

    private final RacingGameService racingGameService;

    public WebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/plays")
    public List<GameResultResponseDto> getHistory() {
        return racingGameService.getHistory();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/plays")
    public GameResultResponseDto runRacingGame(@Valid @RequestBody UserRequestDto inputDto) {
        return racingGameService.getResult(inputDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> badRequest(final RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> invalidInput(final MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unexpectedError(final Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.internalServerError().body("예기치 못한 에러가 발생했습니다.");
    }

}
