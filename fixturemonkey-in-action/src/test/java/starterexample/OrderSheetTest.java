package starterexample;

import static org.junit.jupiter.api.Assertions.*;

import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.javax.validation.plugin.JavaxValidationPlugin;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class OrderSheetTest {
    LabMonkey labMonkey = LabMonkey.labMonkeyBuilder()
        .plugin(new JavaxValidationPlugin())
        .build();

    @Test
    void 처음_테스트() throws Exception {
        OrderSheet orderSheet = labMonkey.giveMeOne(OrderSheet.class);
        log.info(orderSheet.getBackUrl());
        log.info(orderSheet.getId());
        log.info(orderSheet.getRegisteredDateTime().toString());
        log.info(orderSheet.getUserNo().toString());
    }
}