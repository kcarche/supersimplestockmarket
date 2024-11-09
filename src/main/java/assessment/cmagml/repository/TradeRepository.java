package assessment.cmagml.repository;

import assessment.cmagml.model.TradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeModel, Long> {

    //public TradeModel findByStockSymbol(String stockSymbol);

}
