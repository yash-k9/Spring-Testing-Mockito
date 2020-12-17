package com.git.project.springunittesting;

import com.git.project.springunittesting.DAO.UserRepository;
import com.git.project.springunittesting.Model.User;
import com.git.project.springunittesting.Service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringUnitTestingApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllUsers(){
		when(userRepository.findAll()).thenReturn(Stream.of(new User(1, "Ajay", "Dev", "Banglore")
		,new User(2, "Sundar", "DevOps", "Banglore")).collect(Collectors.toList()));

		assertEquals(2, userService.getAll().size());
	}


	@Test
	public void testAddUser(){
		User user = new User(1, "Shawn", "Product Manager", "NewYork");
		when(userRepository.save(user)).thenReturn(user);

		assertEquals(userService.addUser(user), user);
	}

	@Test
	public void testFindUserById(){
		int id = 1;
		when(userRepository.findById(id)).thenReturn(java.util.Optional.of(new User(1, "Ajay", "Developer", "Banglore")));

		Optional<User> optionalUser = userService.getUserByID(id);
		User user = optionalUser.get();

		assertEquals(user.getId(), 1);
	}

	@Test
	public void testDeleteUser(){
		User user = new User(12, "Adam", "Designer", "Hyderabad");
		userService.deleteById(user.getId());
		verify(userRepository, times(1)).deleteById(user.getId());
	}

	@Test
	public void testDeleteAll(){
		when(userRepository.findAll()).thenReturn(new ArrayList<User>());
		assertEquals(0, userService.getAll().size());
	}

}
