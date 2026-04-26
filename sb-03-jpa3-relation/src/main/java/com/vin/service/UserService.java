package com.vin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vin.dto.AddressDTO;
import com.vin.dto.CourseDTO;
import com.vin.dto.UserDTO;
import com.vin.entity.Address;
import com.vin.entity.Course;
import com.vin.entity.Profile;
import com.vin.entity.User;
import com.vin.mappper.UserMapper;
import com.vin.repo.CourseRepository;
import com.vin.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    // =========================
    // CREATE USER
    // =========================
    public UserDTO create(UserDTO dto) {

        User user = UserMapper.toEntity(dto);

        // 🔥 FORCE CREATE (avoid accidental update)
        user.setId(null);

        if (user.getProfile() != null) {
            user.getProfile().setId(null);
        }

        if (user.getAddresses() != null) {
            user.getAddresses().forEach(a -> a.setId(null));
        }

        // =========================
        // M2M HANDLING (Courses)
        // =========================
        if (dto.getCourses() != null) {

            // Case: empty list → clear relation
            if (dto.getCourses().isEmpty()) {
                user.setCourses(new HashSet<>());
            } else {

                List<Long> ids = dto.getCourses().stream()
                        .map(CourseDTO::getId)
                        .toList();

                List<Course> found = courseRepository.findAllById(ids);

                if (found.size() != ids.size()) {
                    throw new RuntimeException("Some courses not found");
                }

                user.setCourses(new HashSet<>(found));
            }
        }

        return UserMapper.toDTO(userRepository.save(user));
    }

    // =========================
    // GET ALL (N+1 FIXED)
    // =========================
    public List<UserDTO> getAll() {
        return userRepository.findAllWithRelations()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    // =========================
    // GET BY ID
    // =========================
    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    // =========================
    // DELETE
    // =========================
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    public UserDTO update(Long id, UserDTO dto) {

        // 1) Fetch existing (managed)
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2) Update scalar fields
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        // =========================
        // 3) O2O → Profile (sync)
        // =========================
        if (dto.getProfile() != null) {

            if (user.getProfile() == null) {
                // create
                Profile p = new Profile();
                p.setBio(dto.getProfile().getBio());
                p.setUser(user);
                user.setProfile(p);
            } else {
                // update
                user.getProfile().setBio(dto.getProfile().getBio());
            }

        } else {
            // remove profile if explicitly null
            if (user.getProfile() != null) {
                user.getProfile().setUser(null);
                user.setProfile(null);
            }
        }

        // =========================
        // 4) O2M → Addresses (diff sync)
        // =========================
        if (dto.getAddresses() != null) {

            // Map existing by id
            Map<Long, Address> existingMap = user.getAddresses().stream()
                    .filter(a -> a.getId() != null)
                    .collect(Collectors.toMap(Address::getId, a -> a));

            List<Address> updatedList = new ArrayList<>();

            for (AddressDTO adto : dto.getAddresses()) {

                if (adto.getId() != null && existingMap.containsKey(adto.getId())) {
                    // update existing
                    Address existing = existingMap.get(adto.getId());
                    existing.setCity(adto.getCity());
                    existing.setState(adto.getState());
                    updatedList.add(existing);

                } else {
                    // add new
                    Address a = new Address();
                    a.setCity(adto.getCity());
                    a.setState(adto.getState());
                    a.setUser(user);
                    updatedList.add(a);
                }
            }

            // replace (orphanRemoval=true will delete removed ones)
            user.getAddresses().clear();
            user.getAddresses().addAll(updatedList);
        }

        // =========================
        // 5) M2M → Courses (replace)
        // =========================
        if (dto.getCourses() != null) {

            if (dto.getCourses().isEmpty()) {
                user.setCourses(new HashSet<>());
            } else {

                List<Long> ids = dto.getCourses().stream()
                        .map(CourseDTO::getId)
                        .toList();

                List<Course> found = courseRepository.findAllById(ids);

                if (found.size() != ids.size()) {
                    throw new RuntimeException("Some courses not found");
                }

                user.setCourses(new HashSet<>(found));
            }
        }

        // 6) Save (dirty checking handles updates)
        return UserMapper.toDTO(userRepository.save(user));
    }
}