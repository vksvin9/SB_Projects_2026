package com.vin.soap;

import com.vin.dto.*;
import com.vin.entity.User;
import com.vin.service.UserService;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@WebService(
        endpointInterface = "com.vin.soap.UserSoapService",
        targetNamespace = "http://soap.vin.com/user"
)
@Service
@RequiredArgsConstructor
public class UserSoapServiceImpl implements UserSoapService {

    private final UserService service;

    // ===== DB =====

    @Override
    public CreateUserResponse createUser(CreateUserRequest req) {
        User u = service.create(User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .build());
        CreateUserResponse res = new CreateUserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        return res;
    }

    @Override
    public GetUserResponse getUser(GetUserRequest req) {
        User u = service.get(req.getId());
        GetUserResponse res = new GetUserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        return res;
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest req) {
        User u = service.update(req.getId(),
                User.builder().name(req.getName()).email(req.getEmail()).build());
        UpdateUserResponse res = new UpdateUserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        return res;
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest req) {
        service.delete(req.getId());
        DeleteUserResponse res = new DeleteUserResponse();
        res.setStatus("DELETED");
        return res;
    }

    // ===== API =====

    @Override
    public GetUserResponse getUserFromApi(GetUserRequest req) {
        var u = service.getFromApi(req.getId());
        GetUserResponse res = new GetUserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        return res;
    }

    @Override
    public CreateUserResponse createUserFromApi(CreateUserRequest req) {
        var u = service.createFromApi(req.getName(), req.getEmail());
        CreateUserResponse res = new CreateUserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        return res;
    }

    @Override
    public UpdateUserResponse updateUserFromApi(UpdateUserRequest req) {
        var u = service.updateFromApi(req.getId(), req.getName(), req.getEmail());
        UpdateUserResponse res = new UpdateUserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        return res;
    }

    @Override
    public DeleteUserResponse deleteUserFromApi(DeleteUserRequest req) {
        service.deleteFromApi(req.getId());
        DeleteUserResponse res = new DeleteUserResponse();
        res.setStatus("DELETED_FROM_API");
        return res;
    }
}