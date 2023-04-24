package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.car.CarDaoWebImpl;
import racingcar.dao.gameresult.GameResultDaoWebImpl;
import racingcar.domain.Car;
import racingcar.domain.Names;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.utils.DtoMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
//@Import({CarDaoWebImpl.class, GameResultDaoWebImpl.class})
class CarDaoWebImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AnnotationConfigApplicationContext ac;

    private CarDaoWebImpl carDaoWebImpl;
    private GameResultDaoWebImpl gameResultDaoWebImpl;


    @BeforeEach
    void setUp() {
        carDaoWebImpl = new CarDaoWebImpl(jdbcTemplate);
        gameResultDaoWebImpl = new GameResultDaoWebImpl(jdbcTemplate);
    }

    @Test
    void save() {
        final RacingGame racingGame = new RacingGame(new Names("헙크, 채채"), new TryCount(3));
        racingGame.run(new DefaultMovingStrategy());
        Long gameResultId = gameResultDaoWebImpl.save(DtoMapper.toRacingGameDto(racingGame));
        assertThat(1L).isEqualTo(gameResultId);

        Car car = new Car("헙크", 3, true);
        Long carId = carDaoWebImpl.save(DtoMapper.mapToCarDto(gameResultId, car));
        assertThat(1L).isEqualTo(carId);
    }


    @Test
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " / beanDefinition = " + beanDefinition);
            }
        }
    }
}
