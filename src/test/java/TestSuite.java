import assessment.cmagml.service.ServiceLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSuite {

    ServiceLayer testServiceLayer = new ServiceLayer();


    @Test
    public void testCalculateDividendYieldPOPExample() {
        Assertions.assertEquals(0.08, testServiceLayer.calculateDividendYield(100, 8), 0.0, "Dividend Yield should be 0.08");
    }

}
