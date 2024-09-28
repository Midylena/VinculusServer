package com.mechachieve.vinculus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mechachieve.vinculus.user.User;

@Repository
public interface ImagemDaoVinculusPost extends JpaRepository<User, Long>{
	Optional<User> findByLogin(String login);
}
