package com.vin.soap;

import com.vin.dto.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(
        name = "UserService",
        targetNamespace = "http://soap.vin.com/user"
)
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface UserSoapService {

    // =========================
    // 🔹 EXISTING DB METHODS
    // =========================

    @WebMethod
    @WebResult(name = "CreateUserResponse")
    CreateUserResponse createUser(
            @WebParam(name = "CreateUserRequest") CreateUserRequest request);

    @WebMethod
    @WebResult(name = "GetUserResponse")
    GetUserResponse getUser(
            @WebParam(name = "GetUserRequest") GetUserRequest request);

    @WebMethod
    @WebResult(name = "UpdateUserResponse")
    UpdateUserResponse updateUser(
            @WebParam(name = "UpdateUserRequest") UpdateUserRequest request);

    @WebMethod
    @WebResult(name = "DeleteUserResponse")
    DeleteUserResponse deleteUser(
            @WebParam(name = "DeleteUserRequest") DeleteUserRequest request);


    // =========================
    // 🔹 NEW API METHODS (UNIQUE XML NAMES)
    // =========================

    @WebMethod
    @WebResult(name = "GetUserFromApiResponse")
    GetUserResponse getUserFromApi(
            @WebParam(name = "GetUserFromApiRequest") GetUserRequest request);

    @WebMethod
    @WebResult(name = "CreateUserFromApiResponse")
    CreateUserResponse createUserFromApi(
            @WebParam(name = "CreateUserFromApiRequest") CreateUserRequest request);

    @WebMethod
    @WebResult(name = "UpdateUserFromApiResponse")
    UpdateUserResponse updateUserFromApi(
            @WebParam(name = "UpdateUserFromApiRequest") UpdateUserRequest request);

    @WebMethod
    @WebResult(name = "DeleteUserFromApiResponse")
    DeleteUserResponse deleteUserFromApi(
            @WebParam(name = "DeleteUserFromApiRequest") DeleteUserRequest request);
}