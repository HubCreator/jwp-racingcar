package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.request.UserRequestDto;
import racingcar.dto.response.CarResponseDto;
import racingcar.dto.response.GameResultResponseDto;
import racingcar.service.RacingGameService;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
public class WebControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingGameService service;

    @Test
    void test() throws Exception {
        final UserRequestDto requestDto = new UserRequestDto("헙크, 채채", 10);

        final List<CarResponseDto> carResponseDtos = List.of(
                new CarResponseDto("헙크", 5),
                new CarResponseDto("채채", 10)
        );
        final GameResultResponseDto resultDto = new GameResultResponseDto(List.of("채채"), carResponseDtos);

        when(service.getResult(requestDto)).thenReturn(resultDto);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(requestDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value(String.join(", ", resultDto.getWinners())))
                .andExpect(jsonPath("$.racingCars[0].name").value("헙크"))
                .andExpect(jsonPath("$.racingCars[0].position").value(5))
                .andExpect(jsonPath("$.racingCars[1].name").value("채채"))
                .andExpect(jsonPath("$.racingCars[1].position").value(10));

        verify(service, times(1)).getResult(requestDto);
    }
}
