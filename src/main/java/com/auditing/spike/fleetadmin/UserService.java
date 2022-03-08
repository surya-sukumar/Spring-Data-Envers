package com.auditing.spike.fleetadmin;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    private List<User> findUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    private User createUser(@RequestBody User user) {
        log.info("User "+user+" saved");
        userRepository.save(user);
        return user;
    }

    @PutMapping("/user/{id}")
    private User updateUser(@PathVariable Long id, @RequestBody User user) {

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            throw new RuntimeException("User id does not exist");
        User user1 = optionalUser.get();
        user1.setName(user.getName());
        user1.setTech(user.getTech());
        userRepository.save(user1);
        return user1;
    }

    @DeleteMapping("/user/{id}")
    private User deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            throw new RuntimeException("User id does not exist");
        User user = optionalUser.get();
        userRepository.deleteById(id);
        return user;
    }

    @GetMapping("/getInfo/user/{id}")
    private ResponseEntity<List<AuditEntity>> getInfo(@PathVariable Long id) {
        System.out.println(userRepository.findRevisions(Long.valueOf(id)));
        List<AuditEntity> results = userRepository.findUserHistory(id);
        return ResponseEntity.ok(results);
    }
}
