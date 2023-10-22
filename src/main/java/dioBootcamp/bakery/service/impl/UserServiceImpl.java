package dioBootcamp.bakery.service.impl;

import dioBootcamp.bakery.domain.model.User;
import dioBootcamp.bakery.domain.repository.UserRepository;
import dioBootcamp.bakery.service.interfaces.CrudService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements CrudService<User> {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        if(user.getId() != null && userRepository.existsById(user.getId())){
            throw new IllegalArgumentException("This user already exists.");
        }
        if(userRepository.existsByCardNumber(user.getPaymentInfo().getCardNumber())) {
            throw new IllegalArgumentException("Card already exists on a user.");
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean delete(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            throw new NoSuchElementException("Id not found");
        }
    }

    @Override
    public User update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            User userToUpdate;

            userToUpdate = user;

            userRepository.save(userToUpdate);

            return userToUpdate;
        } else {
            throw new NoSuchElementException("Id not found.");
        }

    }
}
