package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, String> {
    Optional<SiteUser> findByLoginID(String username);



    SiteUser findByNumber(Long buyer_id);
}
