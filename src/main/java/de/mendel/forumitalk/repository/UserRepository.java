package de.mendel.forumitalk.repository;

import de.mendel.forumitalk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);



}
