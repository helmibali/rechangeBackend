package com.helmi.TunningMarket;

import com.helmi.TunningMarket.entities.Role;
import com.helmi.TunningMarket.entities.User;
import com.helmi.TunningMarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*import springfox.documentation.swagger2.annotations.EnableSwagger2;

 */

import javax.annotation.PostConstruct;

/*
@EnableSwagger2

 */

@SpringBootApplication
public class TunningMarketApplication {
	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(TunningMarketApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*
	@PostConstruct
	void init_users() {
//ajouter les rôles
		userService.addRole(new Role(null,"ADMIN"));
		userService.addRole(new Role(null,"USER"));
//ajouter les users
		userService.saveUser2(new User(null,"admin","123",true,null));
		userService.saveUser2(new User(null,"helmi","123",true,null));
		userService.saveUser2(new User(null,"saif","123",true,null));
		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("admin", "USER");
		userService.addRoleToUser("helmi", "USER");
		userService.addRoleToUser("saif", "USER");
	}


 */

}
