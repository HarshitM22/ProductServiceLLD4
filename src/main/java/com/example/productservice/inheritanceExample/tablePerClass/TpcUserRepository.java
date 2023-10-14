package com.example.productservice.inheritanceExample.tablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TpcUserRepository extends JpaRepository<User,Long> {
    User save(User user);
    User findUserById(Long id);
}
