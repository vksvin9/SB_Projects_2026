package com.vin.controller;

import com.vin.dto.*;
import com.vin.entity.User;
import com.vin.service.UserService;
import com.vin.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {

    private static final String NAMESPACE = "http://vin.com/user";

    private final UserService service;

    @PayloadRoot(namespace = NAMESPACE, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse create(@RequestPayload CreateUserRequest req) {

        User u = service.create(User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .build());

        CreateUserResponse res = new CreateUserResponse();
        res.setUser(UserMapper.toDto(u));
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse get(@RequestPayload GetUserRequest req) {

        User u = service.get(req.getId());

        GetUserResponse res = new GetUserResponse();
        res.setUser(UserMapper.toDto(u));
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse update(@RequestPayload UpdateUserRequest req) {

        User u = service.update(req.getId(),
                User.builder()
                        .name(req.getName())
                        .email(req.getEmail())
                        .build());

        UpdateUserResponse res = new UpdateUserResponse();
        res.setUser(UserMapper.toDto(u));
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse delete(@RequestPayload DeleteUserRequest req) {

        service.delete(req.getId());

        DeleteUserResponse res = new DeleteUserResponse();
        res.setStatus("DELETED");
        return res;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAll(@RequestPayload GetAllUsersRequest req) {

        GetAllUsersResponse res = new GetAllUsersResponse();

        res.setUsers(
                service.getAll()
                        .stream()
                        .map(UserMapper::toDto)
                        .collect(Collectors.toList())
        );

        return res;
    }
}