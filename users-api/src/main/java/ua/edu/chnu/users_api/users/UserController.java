package ua.edu.chnu.users_api.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> readAll() {
        var users = service.readAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> read(@PathVariable Long id) {
        User user = service.read(id);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest user) {
        User created = service.create(toModel(user));
        return created == null ? ResponseEntity.status(HttpStatus.CONFLICT).build() :
                ResponseEntity.ok(toResponse(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserRequest user) {
        return switch (service.update(id, toModel(user))) {
            case -1 -> ResponseEntity.status(HttpStatus.CONFLICT).build();
            case 0 -> ResponseEntity.notFound().build();
            case 1 -> ResponseEntity.noContent().build();
            default -> ResponseEntity.badRequest().build();
        };
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest user) {
        String token = service.login(user.username(), user.password());
        return token == null ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() : ResponseEntity.ok(token);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getUsername());
    }

    private User toModel(UserRequest user) {
        return new User(user.username(), user.password());
    }
}
