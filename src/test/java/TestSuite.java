import assessment.cmagml.Main;
import assessment.cmagml.model.TradeRequest;
import assessment.cmagml.service.ServiceLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
public class TestSuite {

    @Autowired
    ServiceLayer testServiceLayer;

    @Test
    public void testCalculateDividendYieldPOPExample() {
        Assertions.assertEquals(0.08, testServiceLayer.calculateDividendYield(100, "POP"), 0.0, "Dividend Yield must be 0.08");
    }

    @Test
    public void testCalculateDividendYieldALEExample() {
        Assertions.assertEquals(0.115, testServiceLayer.calculateDividendYield(200, "ALE"), 0.0, "Dividend Yield must be 0.115");
    }

    @Test
    public void testCalculateDividendYieldGINExample() {
        Assertions.assertEquals(0.04, testServiceLayer.calculateDividendYield(50, "GIN"), 0.0, "Dividend Yield must be 0.4");
    }

    @Test
    public void testCalculateDividendYieldJOEExample() {
        Assertions.assertEquals(0.0433, testServiceLayer.calculateDividendYield(300, "JOE"), 0.001, "Dividend Yield must be 0.02");
    }

    @Test
    public void testCalculateDividendYieldTeaExample() {
        Assertions.assertEquals(0.0, testServiceLayer.calculateDividendYield(100, "TEA"), 0.0, "Dividend Yield must be 0.08");
    }

    @Test
    public void testCalculatePERatioPOPExample(){
        Assertions.assertEquals(37.5, testServiceLayer.calculatePERatio(300, "POP"), 0.0, "PE Ratio should be 37.5");
    }

    @Test
    public void testCalculatePERationALEExample(){
        Assertions.assertEquals(21.739, testServiceLayer.calculatePERatio(500, "ALE"), 0.001, "PE Ratio should be 21.739");
    }

    @Test
    public void testCalculatePERationGINExample(){
        Assertions.assertEquals(7.5, testServiceLayer.calculatePERatio(60, "GIN"), 0.0, "PE Ratio should be 7.5");
    }

    @Test
    public void testCalculatePERationJOEExample(){
        Assertions.assertEquals(1.538, testServiceLayer.calculatePERatio(20, "JOE"), 0.001, "PE Ratio should be 1.538");
    }

    @Test
    public void testCalculatePERationTEAExample(){
        Assertions.assertEquals(0.0, testServiceLayer.calculatePERatio(100, "TEA"), 0.0, "PE Ratio should be 0.0");
    }

    @Test
    public void recordTradeTest1(){
        TradeRequest tradeRequest = new TradeRequest("POP", 100, "BUY", 100);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeTest2(){
        TradeRequest tradeRequest = new TradeRequest("ALE", 30, "SELL", 40);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeTest3(){
        TradeRequest tradeRequest = new TradeRequest("TEA", 10, "SELL", 100);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeTest4(){
        TradeRequest tradeRequest = new TradeRequest("GIN", 200, "SELL", 8000);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeTest5(){
        TradeRequest tradeRequest = new TradeRequest("JOE", 788, "BUY", 7000);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeTest6(){
        TradeRequest tradeRequest = new TradeRequest("TEA", 65, "SELL", 80);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeTest7(){
        TradeRequest tradeRequest = new TradeRequest("TEA", 35, "BUY", 20);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Completed", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void recordTradeFailTest1(){
        TradeRequest tradeRequest = new TradeRequest("WAT", 100, "SELL", 40);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertSame("Trade Incomplete: Stock Symbol Invalid", testServiceLayer.recordTrade(tradeRequest), "Trade must be recorded");
    }

    @Test
    public void calculateVolumeWeightedStockPriceTeaExample(){
        Assertions.assertEquals(62.7272, testServiceLayer.calculateVolumeWeightedStockPrice("TEA"), 0.0001, "Volume Weighted Stock Price for TEA should be 62.7272, test must be run with all other tests");
    }

    @Test
    public void calculateGeoMeanTest(){
        TradeRequest tradeRequest = new TradeRequest("TEA", 10, "SELL", 100);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("TEA", 65, "SELL", 80);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("TEA", 35, "BUY", 20);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("POP", 100, "BUY", 800);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("POP", 50, "BUY", 600);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("POP", 200, "BUY", 1000);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("ALE", 10, "BUY", 100);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("ALE", 50, "SELL", 600);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("GIN", 30, "SELL", 75);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("JOE", 10, "BUY", 10000);
        testServiceLayer.recordTrade(tradeRequest);
        tradeRequest = new TradeRequest("JOE", 10, "SELL", 5000);
        testServiceLayer.recordTrade(tradeRequest);
        Assertions.assertEquals(6.1835, testServiceLayer.calculateGeometricMean(), 0.0001, "Geometric Mean should be 6.1835");
    }

    @Test
    public void validateTradeRequestIncorrectSaleIndicator(){
        TradeRequest tradeRequest = new TradeRequest("POP", 100, "BUYER", 100);
        Assertions.assertSame("Trade Incomplete: Invalid SaleIndicator Type", testServiceLayer.validateTradeRequest(tradeRequest), "Trade request should return invalid sale indicator warning");
    }

    @Test
    public void validateTradeRequestInvalidPrice(){
        TradeRequest tradeRequest = new TradeRequest("ALE", 100, "BUY", -100);
        Assertions.assertSame("Trade Incomplete: Price must be greater than 0", testServiceLayer.validateTradeRequest(tradeRequest), "Trade request should return invalid price warning");
    }

    @Test
    public void validateTradeRequestInvalidQuantity(){
        TradeRequest tradeRequest = new TradeRequest("GIN", -100, "BUY", 100);
        Assertions.assertSame("Trade Incomplete: Quantity must be greater than 0", testServiceLayer.validateTradeRequest(tradeRequest), "Trade request should return invalid quantity warning");
    }

    @Test
    public void validateTradeRequestValid(){
        TradeRequest tradeRequest = new TradeRequest("JOE", 100, "SELL", 100);
        Assertions.assertSame("Trade Valid", testServiceLayer.validateTradeRequest(tradeRequest), "Trade request should return valid");
    }
}

