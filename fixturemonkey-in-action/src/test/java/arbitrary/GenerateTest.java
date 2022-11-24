package arbitrary;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.navercorp.fixturemonkey.FixtureMonkey;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class GenerateTest {
    private FixtureMonkey fixtureMonkey = FixtureMonkey.create();

    @ToString
    static class Order1 {
        private String orderName;
        private Integer orderPrice;
    }

    @Test
    void 기본_생성_전략_필드에_null값을채워줌() throws Exception {
        Order1 order1 = fixtureMonkey.giveMeOne(Order1.class);
        List<Order1> order1List = fixtureMonkey.giveMe(Order1.class, 5);

        log.info(order1.toString());
        log.info("================================");
        log.info(order1List.toString());
    }

    @ToString
    static class Order2 {
        private String orderName;
        private Integer orderPrice;

        public Order2(String orderName, Integer orderPrice) {
            this.orderName = orderName;
            this.orderPrice = orderPrice;
        }
    }

    @Test
    void 리플렉션을_사용하기때문에_기본생성자_없이_생성하면예외발생() throws Exception {
        assertThatThrownBy(() -> {
            fixtureMonkey.giveMeOne(Order2.class);
        }).isInstanceOf(NoSuchMethodException.class);
    }

}
