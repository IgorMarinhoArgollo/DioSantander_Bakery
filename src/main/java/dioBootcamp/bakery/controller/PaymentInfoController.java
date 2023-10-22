package dioBootcamp.bakery.controller;

import dioBootcamp.bakery.domain.model.PaymentInfo;
import dioBootcamp.bakery.service.impl.PaymentInfoServiceImpl;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/paymentInfo")
public class PaymentInfoController {

    @Autowired
    private PaymentInfoServiceImpl paymentInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInfo> findById(@PathVariable Long id){
        var paymentInfo = paymentInfoService.findById(id);
        return ResponseEntity.ok(paymentInfo);
    }

    @GetMapping
    public ResponseEntity<List<PaymentInfo>> findAll(){
        List<PaymentInfo> paymentInfo = paymentInfoService.findAll();
        return ResponseEntity.ok(paymentInfo);
    }

    @PostMapping()
    public ResponseEntity<PaymentInfo> create(@RequestBody PaymentInfo paymentInfo) {
        var paymentInfoCreated = paymentInfoService.create(paymentInfo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(paymentInfoCreated.getId())
            .toUri();
        return ResponseEntity.created(location).body(paymentInfoCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentInfo> update(@PathVariable Long id, @RequestBody PaymentInfo updatedPaymentInfo) {
        PaymentInfo paymentInfo = paymentInfoService.update(updatedPaymentInfo);
        return ResponseEntity.ok(paymentInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = paymentInfoService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
