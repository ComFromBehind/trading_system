package com.example.demo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public SiteUser create(String id, Integer bg, String pw){
        SiteUser user = new SiteUser();
        user.setLoginID(id);
        user.setBudget(bg);

        user.setPassword(passwordEncoder.encode(pw));
        this.userRepository.save(user);
        return user;
    }

}
