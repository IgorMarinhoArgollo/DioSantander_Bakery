package dioBootcamp.bakery.domain.repository;

import dioBootcamp.bakery.domain.model.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {

}
