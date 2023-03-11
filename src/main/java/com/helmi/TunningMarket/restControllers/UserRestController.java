package com.helmi.TunningMarket.restControllers;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.User;
import com.helmi.TunningMarket.repositories.RoleRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.UserRequest;
import com.helmi.TunningMarket.services.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;


@RestController
@CrossOrigin(origins = "*")
public class UserRestController {
	@Autowired
	UserRepository userRep;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private ServletContext context;
	@Autowired
	UserService userService;


	@GetMapping("/u")
	public List<User> usersss(){
		return userRep.PublicUser();
	}
	@GetMapping("api/infoUserById/{id}")
	public User getUserById(@PathVariable Long id){
		return userRep.findById(id).get();
	}
	@RequestMapping(value ="api/info/{username}",method = RequestMethod.GET)
	public User getUserByUsernamePublic(@PathVariable("username") String username) {
		return userRep.findByUsername(username);
	}

	
	@RequestMapping(path = "all",method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRep.findAll();
	 }

	@RestController
	public class PingRestController {

		@RequestMapping(method = RequestMethod.GET, path = "/api/ping")
		public ResponseEntity<String> getPing() {
			return ResponseEntity.ok("OK");
		}
	}


}
