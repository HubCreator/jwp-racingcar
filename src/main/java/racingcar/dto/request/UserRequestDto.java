package racingcar.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public final class UserRequestDto {

    @Size(min = 1, max = 30, message = "이름은 1~30자 사이여야 합니다.")
    private String names;

    @NotNull(message = "시도 횟수를 입력해야 합니다.")
    @Positive(message = "0 또는 음수가 올 수 없습니다.")
    private Integer count;

    private UserRequestDto() {
    }

    public UserRequestDto(final String names, final Integer count) {
        this.names = names;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}

