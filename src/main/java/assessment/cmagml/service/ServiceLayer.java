package assessment.cmagml.service;

public class ServiceLayer {


    public double calculateDividendYield(double price, double lastDividend) {

        double dividendYield = lastDividend / price;

        return dividendYield;
    }
}
