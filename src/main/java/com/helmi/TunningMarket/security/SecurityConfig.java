package com.helmi.TunningMarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
 	@Autowired
 	UserDetailsService userDetailsService;
 	
 	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	
    }
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("api/user/add");
	}
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable();
	  http.sessionManagement().
	  			sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  http.authorizeRequests()
			  .antMatchers(HttpMethod.POST,"/login").permitAll()
			  .antMatchers("/sign-up").permitAll()
			  .antMatchers("/sign-up-with-img").permitAll()
//Contact usernames
			  .antMatchers(HttpMethod.POST,"/api/contact").permitAll()
			  .antMatchers(HttpMethod.GET,"/api/produits").permitAll()
			  .antMatchers(HttpMethod.GET,"/api/usernames").permitAll()
			  .antMatchers(HttpMethod.GET,"/api/**").permitAll();


	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/commande/add").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.GET,"/all").hasAuthority("ADMIN");

//User inscription
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/inscription").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/ping").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/email").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/usertoken/{username}").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/useractive/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/social").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/signup").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/signupwithimg").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/userPw/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/user/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/userImg/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/userPw/{id}").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/userPwEmail/{id}").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/user/{id}").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/userImg/{id}").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/user/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/user/{id}").hasAuthority("USER");
	  //Message
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/message").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/message-read/{id}").hasAuthority("USER");
	  //Cart

	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/commande/etat/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/commande").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/add-to-cart").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/panier").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/panier2").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/cart/{id}").hasAuthority("USER");
      //Produits


	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/activeProduit/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/produit/add").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/produit").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/produit/addd").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/produit/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/produit/delete/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/produit/add").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api//produit/addd").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/produit/{id}").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/produit/delete/{id}").hasAuthority("USER");
//Modele
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/modele/add").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/modele/update/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/modele/delete/{id}").hasAuthority("ADMIN");
//Gouvernorat
	 // http.authorizeRequests().antMatchers(HttpMethod.DELETE,"modele/delete/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/gouvernorat").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/gouvernorat/{id}").hasAuthority("ADMIN");

  //Delegation
	  // http.authorizeRequests().antMatchers(HttpMethod.DELETE,"modele/delete/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/delegations").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/delegation").hasAuthority("ADMIN");
	 // http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/gouvernorat/{id}").hasAuthority("ADMIN");

//Marque
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/marque").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/pro").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/image").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/brand").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/addMarque").permitAll();
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/marque").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/marque/{id}").hasAuthority("ADMIN");
//Categorie
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/categorie/add").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/categorie/update/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/categorie/delete/{id}").hasAuthority("ADMIN");
//Famille
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/famille").hasAuthority("ADMIN");
	 // http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/categorie/update/{id}").hasAuthority("ADMIN");
	 // http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/categorie/delete/{id}").hasAuthority("ADMIN");
//Article
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/articleWithImg").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/articleWithImg").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/articleflickr").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/article").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/article").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/article/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/article").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/article").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/article/{id}").hasAuthority("USER");

  //Comment
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/comment").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/comment1").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/comment/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/comment/{id}").hasAuthority("ADMIN");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/comment").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/comment1").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/comment/{id}").hasAuthority("USER");
	  http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/comment/{id}").hasAuthority("USER");

  //Contact
	  http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/contact/liste").hasAuthority("ADMIN");
	  http.authorizeRequests().anyRequest().authenticated();
	  http.addFilter(new  JWTAuthenticationFilter (authenticationManager())) ;
	  http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);

		 
  }


}
