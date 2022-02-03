package br.com.jopaulo.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.jopaulo.domain.User;
import br.com.jopaulo.domain.dto.UserDTO;
import br.com.jopaulo.services.impl.UserServiceImpl;

@SpringBootTest
class UserResourceTest {
	
	private static final int INDEX = 0;
	private static final String MESSAGE = "Usuário não encontrado";
	private static final Integer ID = 1;
	private static final String NAME = "João Paulo";
	private static final String EMAIL = "jp_cbc@hotmail.com";
	private static final String PASSWORD = "1234";
	
	@InjectMocks
	private UserResource resource;
	
	@Mock
	private UserServiceImpl service;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindAllReturnListOfUserDto() {
		when(service.findAll()).thenReturn(List.of(user));
		when(mapper.map(any(), any())).thenReturn(userDTO);
		
		ResponseEntity<List<UserDTO>> response = resource.findAll();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
		
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(NAME, response.getBody().get(INDEX).getName());
		assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
		assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}

}
