package lombokwithjava;

import static org.assertj.core.api.Assertions.*;

import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.api.introspector.BeanArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.BuilderArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.plugin.JacksonPlugin;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hibernate.validator.constraints.Length;
import org.junit.jupiter.api.Test;

@Slf4j
public class LombokWithJavaTest {

    private LabMonkey labMonkey = LabMonkey.labMonkeyBuilder()
        .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
        .build();

    @Value
    static class Price {
        private Double priceRate;
    }

    @Test
    void 롬복_밸류를사용하는_불변객체의경우_롬복컨피그로constructorproperties를_설정하고_객체검사자를_지정해준다() throws Exception {
        Price price = labMonkey.giveMeOne(Price.class);
        log.info(price.toString());
    }

    private LabMonkey labMonkey2 = LabMonkey.labMonkeyBuilder()
        .plugin(new JacksonPlugin())
        .build();

    @Test
    void 롬복_밸류를사용하는_불변객체를_잭슨검사자로_생성() throws Exception {
        Price price = labMonkey2.giveMeOne(Price.class);
        log.info(price.toString());
    }

    @ToString
    static class Price2 {
        private String priceTag;
        private Double priceRate;

        @Builder
        public Price2(String priceTag, Double priceRate) {
            this.priceTag = priceTag;
            this.priceRate = priceRate;
        }
    }

    private LabMonkey labMonkey3 = LabMonkey.labMonkeyBuilder()
        .objectIntrospector(BuilderArbitraryIntrospector.INSTANCE)
        .build();

    @Test
    void 롬복_빌더를_사용한경우_생성() throws Exception {
        Price2 price2 = labMonkey3.giveMeOne(Price2.class);
        log.info(price2.toString());
        assertThatThrownBy(() -> {
            Price2 price21 = labMonkey.giveMeOne(Price2.class);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @NoArgsConstructor
    @ToString
    static class Price3 {
        private String priceTag;
        private Double priceRate;

        public Price3(String priceTag, Double priceRate) {
            this.priceTag = priceTag;
            this.priceRate = priceRate;
        }
    }

    LabMonkey labMonkey4 = LabMonkey.labMonkeyBuilder()
        .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
        .build();

    @Test
    void 기본생성자를_롬보으로_지정한경우_생성() throws Exception {
        Price3 price3 = labMonkey4.giveMeOne(Price3.class);
        log.info(price3.toString());
        assertThatThrownBy(() -> {
            labMonkey.giveMeOne(Price3.class);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
