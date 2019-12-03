package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements DataLoader1 {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole=roleRepository.findByRole("ADMIN");
        Role userRole=roleRepository.findByRole("USER");

        User user=new User("jim@jim.com","pass","Jim","Jimmerson", true,"jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user=new User("admin@admin.com","pass","Admin","User", true,"admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }
}
