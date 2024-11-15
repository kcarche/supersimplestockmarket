package assessment.cmagml.repository;

import assessment.cmagml.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository extends JpaRepository<StockModel, Long> {

    public StockModel findByStockSymbol(String stockSymbol);
}
