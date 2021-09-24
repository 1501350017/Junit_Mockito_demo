package com.javatechie.spring.mockito.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.javatechie.spring.mockito.api.dao.UserRepository;
import com.javatechie.spring.mockito.api.model.User;
import com.javatechie.spring.mockito.api.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoApplicationTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	/**
	 * If an user is loaded from the repository, then we compare the expected value and actual value.
	 */
	@Test
	public void getUsersTest() {
	     //
		//Given
		//

		//
		//When
		//
		when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
		//
		//Then
		//
		assertEquals(2, service.getUsers().size());
		verify(repository, times(1)).findAll();
	}


	/**
	 * If an  user is loaded from the repository, then we are getting the user by their address.
	 */
	@Test
	public void getUserbyAddressTest() {

         //
		//Given
		//
		String address = "Bangalore";


        //
		//When
		//
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		//
		//Then
		//
		assertEquals(1, service.getUserbyAddress(address).size());
		verify(repository, times(1)).findByAddress(address);
	}

	/**
	 * If an user is loaded from the repository, then with are returning the save user.
	 */
	@Test
	public void saveUserTest() {
         //
		//Given
		//
		User user = new User(999, "Pranya", 33, "Pune");

		//
		//When
		//
		when(repository.save(user)).thenReturn(user);

		//
		//Then
		//
		assertEquals(user, service.addUser(user));
		verify(repository, times(1));
	}

	/**
	 * If an user is loaded from the repository, then we are deleting the user.
	 */
	@Test
	public void deleteUserTest() {
		//
		//Given
		//
		User user = new User(999, "Pranya", 33, "Pune");

         //
		//When
		//
		service.deleteUser(user);

        //
		//Then
		//
		verify(repository, times(1)).delete(user);

	}

}
