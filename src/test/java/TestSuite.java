import assessment.cmagml.Main;
import assessment.cmagml.repository.StockRepository;
import assessment.cmagml.service.ServiceLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= Main.class)
public class TestSuite {

    @Autowired
    ServiceLayer testServiceLayer;

    @Autowired
    StockRepository testStockRepository;

    @Test
    public void testCalculateDividendYieldPOPExample() {
        Assertions.assertEquals(0.08, testServiceLayer.calculateDividendYield(100, "POP"), 0.0, "Dividend Yield should be 0.08");
    }

    @Test
    public void testCalculateDividendYieldALEPrice() {
        Assertions.assertEquals(0.115, testServiceLayer.calculateDividendYield(200, "ALE"), 0.0, "Dividend Yield should be 0.115");
    }

    @Test
    public void testCalculateDividendYieldGINPrice() {
        Assertions.assertEquals(0.04, testServiceLayer.calculateDividendYield(50, "GIN"), 0.0, "Dividend Yield should be 0.4");
    }
}
