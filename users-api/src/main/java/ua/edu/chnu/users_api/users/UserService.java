package ua.edu.chnu.users_api.users;

import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<User> readAll() {
        return repository.findAll();
    }

    public User read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public int update(Long id, User user) {
        if (read(id) == null) {
            return 0;
        }

        if (repository.existsByUsername(user.getUsername())) {
            return -1;
        }

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return 1;
    }

    public boolean delete(Long id) {
        User user = read(id);
        if (user == null) {
            return false;
        }

        repository.delete(user);
        return true;
    }

    public String login(String username, String password) {
        User user = repository.findByUsername(username).orElse(null);
        return user != null && passwordEncoder.matches(password, user.getPassword()) ?
                Jwts.builder().subject(user.getUsername()).signWith(Jwts.SIG.HS256.key().build()).compact() : null;
    }
}
