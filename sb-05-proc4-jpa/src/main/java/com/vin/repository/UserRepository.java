package com.vin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vin.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // INSERT PROC
    @Procedure(procedureName = "user_db.insert_user_proc")
    void insertUser(@Param("p_name") String name,
                    @Param("p_email") String email);

    // UPDATE PROC
    @Procedure(procedureName = "user_db.update_user_proc")
    void updateUser(@Param("p_id") Long id,
                    @Param("p_name") String name,
                    @Param("p_email") String email);
}