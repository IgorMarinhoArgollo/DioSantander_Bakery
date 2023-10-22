package dioBootcamp.bakery.service.impl;

import dioBootcamp.bakery.domain.model.Product;
import dioBootcamp.bakery.domain.repository.ProductRepository;
import dioBootcamp.bakery.service.interfaces.CrudService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements CrudService<Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product create(Product entity) {
        if(entity.getId() != null && productRepository.existsById(entity.getId())) {
            throw new IllegalArgumentException("Product already exists");
        }
        return productRepository.save(entity);
    }

    @Override
    public Boolean delete(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            throw new NoSuchElementException("Id not found");
        }
    }

    @Override
    public Product update(Product entity) {
        Optional<Product> productOptional = productRepository.findById(entity.getId());
        if (productOptional.isPresent()) {
            Product productToUpdate;

            productToUpdate = entity;

            productRepository.save(productToUpdate);

            return productToUpdate;
        } else {
            throw new NoSuchElementException("Id not found.");
        }
    }
}
