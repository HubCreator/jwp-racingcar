package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import racingcar.dao.gameresult.GameResultDaoWebImpl;
import racingcar.dto.db.GameResultDto;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
@Import(GameResultDaoWebImpl.class)
class GameResultDaoWebImplTest {

    @Autowired
    private GameResultDaoWebImpl gameResultDaoWebImpl;

    @Test
    void save1() {
        Long id = gameResultDaoWebImpl.save(new GameResultDto(3));
        assertThat(1L).isEqualTo(id);
    }

    @Test
    void save2() {
        Long id = gameResultDaoWebImpl.save(new GameResultDto(3));
        assertThat(2L).isEqualTo(id);
    }
}
