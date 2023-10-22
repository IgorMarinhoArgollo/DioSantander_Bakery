package dioBootcamp.bakery.service.impl;


import dioBootcamp.bakery.domain.model.PaymentInfo;
import dioBootcamp.bakery.domain.repository.PaymentInfoRepository;
import dioBootcamp.bakery.service.interfaces.CrudService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentInfoServiceImpl implements CrudService<PaymentInfo> {
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    @Override
    public List<PaymentInfo> findAll() {
        return paymentInfoRepository.findAll();
    }

    @Override
    public PaymentInfo findById(Long id) {
        return paymentInfoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public PaymentInfo create(PaymentInfo entity) {
        if(entity.getId() != null && paymentInfoRepository.existsById(entity.getId())) {
            throw new IllegalArgumentException("Product already exists");
        }
        return paymentInfoRepository.save(entity);
    }

    @Override
    public Boolean delete(Long id) {
        if(paymentInfoRepository.existsById(id)) {
            paymentInfoRepository.deleteById(id);
            return true;
        } else {
            throw new NoSuchElementException("Id not found");
        }
    }

    @Override
    public PaymentInfo update(PaymentInfo entity) {
        Optional<PaymentInfo> paymentInfoOptional = paymentInfoRepository.findById(entity.getId());
        if (paymentInfoOptional.isPresent()) {
            PaymentInfo paymentInfoToUpdate;

            paymentInfoToUpdate = entity;

            paymentInfoRepository.save(paymentInfoToUpdate);

            return paymentInfoToUpdate;
        } else {
            throw new NoSuchElementException("Id not found.");
        }
    }
}
