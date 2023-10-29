package de.mendel.forumitalk.dao;

import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final UserRepository repository;

    @Autowired
    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

}
