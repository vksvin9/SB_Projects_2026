package com.vin.mappper;

import java.util.HashSet;
import java.util.List;

import com.vin.dto.AddressDTO;
import com.vin.dto.CourseDTO;
import com.vin.dto.ProfileDTO;
import com.vin.dto.UserDTO;
import com.vin.entity.Address;
import com.vin.entity.Course;
import com.vin.entity.Profile;
import com.vin.entity.User;

public class UserMapper {

    // ENTITY → DTO
    public static UserDTO toDTO(User user) {

        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());

        // Profile
        if (user.getProfile() != null) {
            ProfileDTO p = new ProfileDTO();
            p.setId(user.getProfile().getId());
            p.setBio(user.getProfile().getBio());
            dto.setProfile(p);
        }

        // Addresses
        if (user.getAddresses() != null) {
            dto.setAddresses(user.getAddresses().stream().map(a -> {
                AddressDTO ad = new AddressDTO();
                ad.setId(a.getId());
                ad.setCity(a.getCity());
                ad.setState(a.getState());
                return ad;
            }).toList());
        }

        // Courses
        if (user.getCourses() != null) {
            dto.setCourses(user.getCourses().stream().map(c -> {
                CourseDTO cd = new CourseDTO();
                cd.setId(c.getId());
                cd.setName(c.getName());
                return cd;
            }).toList());
        }

        return dto;
    }

    // DTO → ENTITY
    public static User toEntity(UserDTO dto) {

        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());

        // O2O
        if (dto.getProfile() != null) {
            Profile profile = new Profile();
            profile.setId(dto.getProfile().getId());
            profile.setBio(dto.getProfile().getBio());

            profile.setUser(user);
            user.setProfile(profile);
        }

        // O2M
        if (dto.getAddresses() != null) {
            List<Address> addresses = dto.getAddresses().stream().map(a -> {
                Address ad = new Address();
                ad.setId(a.getId());
                ad.setCity(a.getCity());
                ad.setState(a.getState());
                ad.setUser(user);
                return ad;
            }).toList();

            user.setAddresses(addresses);
        }

     // M2M (only set IDs, no object creation needed actually)
        if (dto.getCourses() != null) {
            List<Course> courses = dto.getCourses().stream().map(c -> {
                Course course = new Course();
                course.setId(c.getId()); // placeholder only
                return course;
            }).toList();

            user.setCourses(new HashSet<>(courses)); // 🔥 important (Set)
        }

        return user;
    }
}