package com.vin.service;

import com.vin.dto.ExternalUser;
import com.vin.entity.User;
import com.vin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final RestTemplate restTemplate;

    @Value("${external.user-api.base-url}")
    private String baseUrl;

    // =========================
    // 🔹 EXISTING DB METHODS
    // =========================

    public User create(User u) {
        return repo.save(u);
    }

    public User get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User update(Long id, User u) {
        User existing = get(id);
        existing.setName(u.getName());
        existing.setEmail(u.getEmail());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // =========================
    // 🔹 NEW API METHODS
    // =========================

    public ExternalUser getFromApi(Long id) {
        return restTemplate.getForObject(
                baseUrl + "/users/" + id,
                ExternalUser.class
        );
    }

    public List<ExternalUser> getAllFromApi() {
        ExternalUser[] users = restTemplate.getForObject(
                baseUrl + "/users",
                ExternalUser[].class
        );
        return Arrays.asList(users);
    }

    public ExternalUser createFromApi(String name, String email) {

        ExternalUser req = new ExternalUser();
        req.setName(name);
        req.setEmail(email);

        return restTemplate.postForObject(
                baseUrl + "/users",
                req,
                ExternalUser.class
        );
    }

    public ExternalUser updateFromApi(Long id, String name, String email) {

        ExternalUser req = new ExternalUser();
        req.setId(id);
        req.setName(name);
        req.setEmail(email);

        restTemplate.put(baseUrl + "/users/" + id, req);

        return req;
    }

    public String deleteFromApi(Long id) {
        restTemplate.delete(baseUrl + "/users/" + id);
        return "DELETED";
    }
}