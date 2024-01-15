package com.mmd.repository;

import com.mmd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    Member findByUsername(String username);

    @Query("SELECT m FROM Member m JOIN FETCH m.roles WHERE m.username = :username")
    Member findMemberByUsernameWithRole(@Param("username") String username);

}
