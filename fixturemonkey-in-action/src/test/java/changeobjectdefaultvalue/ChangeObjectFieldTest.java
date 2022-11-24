package changeobjectdefaultvalue;

import com.navercorp.fixturemonkey.ArbitraryBuilder;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.LabMonkey;
import java.util.List;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.jqwik.api.Arbitrary;
import org.junit.jupiter.api.Test;

@Slf4j
public class ChangeObjectFieldTest {

    @ToString
    @Setter
    static class Generate {
        String value;
        List<String> values;
    }

    ArbitraryBuilder<Generate> generateBuilder = LabMonkey.create().giveMeBuilder(Generate.class);

    @Test
    void asdf() throws Exception {
        LabMonkey labMonkey = LabMonkey.create();
        Generate generate = labMonkey.giveMeOne(Generate.class);
        log.info(generate.toString());
    }

    @Test
    void 필드값설정테스트() throws Exception {
        generateBuilder.set("value", "test");
        generateBuilder.set("values[*]", "all");
        LabMonkey labMonkey = LabMonkey.labMonkeyBuilder()
            .register(generateBuilder).build();
    }
}
