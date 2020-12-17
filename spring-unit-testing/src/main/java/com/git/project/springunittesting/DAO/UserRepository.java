package com.git.project.springunittesting.DAO;

import com.git.project.springunittesting.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
