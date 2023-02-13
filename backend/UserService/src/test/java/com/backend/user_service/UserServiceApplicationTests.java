package com.backend.user_service;

import com.backend.user_service.controllers.UserController;
import com.backend.user_service.entities.Role;
import com.backend.user_service.entities.User;
import com.backend.user_service.exceptions.GlobalExceptionHandler;
import com.backend.user_service.exceptions.ResourceNotFoundException;
import com.backend.user_service.repositories.RoleRepository;
import com.backend.user_service.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {

	private MockMvc mockMvc;
	ObjectMapper objectMapper=new ObjectMapper();
	ObjectWriter objectWriter=objectMapper.writer();

	@Mock
	private UserService userService;

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserController userController;

	User RECORD_1=new User("1","amitendu","Amitendu","Mallick","amit@gmail.com","Amitendu99@",new ArrayList<>(),new HashSet<>());
	Role ROLE_RECORD=new Role("admin","This role for admin",null);


	@BeforeEach
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc= MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new GlobalExceptionHandler()).build();
	}



	@Test
	public void getUserById_success() throws Exception {
		Mockito.when(userService.getUserById(RECORD_1.getUserId())).thenReturn(RECORD_1);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.address",is(new ArrayList<>())));
	}

	@Test
	public void getUserById_failure() throws Exception {
		Mockito.when(userService.getUserById("2")).thenThrow(new ResourceNotFoundException("User is not found!"));

		mockMvc.perform(MockMvcRequestBuilders
						.get("/user/2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
	}

	@Test
	public void createUser_success() throws Exception{
		Set<Role> roleList=new HashSet<>();
		roleList.add(ROLE_RECORD);
		User newUser=User.builder()
				.userId("2")
				.username("amitendu89")
				.firstName("Amitendu")
				.lastName("Mallick")
				.userEmail("amit67@gmail.com")
				.userPassword("Amitendu")
				.build();

		User newUser2=User.builder()
				.userId("2")
				.username("amitendu89")
				.firstName("Amitendu")
				.lastName("Mallick")
				.userEmail("amit67@gmail.com")
				.userPassword("Amitendu")
				.address(null)
				.roles(roleList)
				.build();
//		Mockito.when(roleRepository.findById(ROLE_RECORD.getRoleName())).thenReturn(Optional.of(ROLE_RECORD));
		Mockito.when(userService.createUser(newUser2)).thenReturn(newUser2);

//		String content=objectWriter.writeValueAsString(newUser2);
//
//		MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/user/create")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content);
//		mockMvc.perform(mockRequest)
//				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.data.roles[0].roleName",is("admin")));
		ResponseEntity<Map<String,Object>> data=userController.createUser(newUser2);
		System.out.println(data.getBody());
		User testUser= (User) data.getBody().get("data");
		assertEquals(HttpStatus.CREATED,data.getStatusCode());
		assertEquals(newUser2,testUser);
		assertEquals(newUser2.getRoles(),testUser.getRoles());
	}

}
