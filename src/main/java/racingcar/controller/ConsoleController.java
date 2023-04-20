package racingcar.controller;

import racingcar.domain.movingstrategy.MovingStrategy;
import racingcar.dto.request.UserRequestDto;
import racingcar.dto.response.GameResultResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public ConsoleController(final InputView inputView, final OutputView outputView, final RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void run(final MovingStrategy movingStrategy) {
        final UserRequestDto userRequestDto = new UserRequestDto(inputView.readCarNames(), inputView.readTryCount());
        final GameResultResponseDto resultDto = racingGameService.getResult(userRequestDto);
        outputView.printTotalMovingStatus(resultDto);
    }
}
