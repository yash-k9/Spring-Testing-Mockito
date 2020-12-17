package com.git.project.springunittesting.Service;

import com.git.project.springunittesting.DAO.UserRepository;
;
import com.git.project.springunittesting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User addUser(User user){
        repository.save(user);
        return user;
    }

    public Optional<User> getUserByID(int id){
        return repository.findById(id);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
