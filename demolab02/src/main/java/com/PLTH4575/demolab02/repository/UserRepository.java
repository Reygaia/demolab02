package com.PLTH4575.demolab02.repository;
import com.PLTH4575.demolab02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PLTH4575.demolab02.service.UserService;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserById(Long id);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
