	package TheCarRentalProject.Configuration;

	import TheCarRentalProject.Repository.UserRepository;
	import TheCarRentalProject.Service.implementations.UserDetailsServiceImpl;
	import TheCarRentalProject.handling.YourAccessDeniedHandler;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


	@Configuration
	public class JwtSecurityConfiguration  {

		@Autowired private UserRepository userRepo;
		@Autowired private JwtTokenFilter jwtTokenFilter;

		@Bean
		public UserDetailsService userDetailsService() {
			return new UserDetailsServiceImpl(userRepo) ;
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}


		@Bean
		public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) {
			 AuthenticationManager build = null;
			 try {
				 build = http.getSharedObject(AuthenticationManagerBuilder.class)
						 .userDetailsService(userDetailService)
						 .passwordEncoder(bCryptPasswordEncoder)
						 .and()
						 .build();
			 } catch (Exception e) {

				 e.printStackTrace();
			 }
			System.out.println(build);
			 return build;
		}

		@Bean
		public SecurityFilterChain configure(HttpSecurity http) throws Exception {

			http.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers("/authenticate", "/docs/**", "/users").permitAll()
							.anyRequest().authenticated()
					);

			http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
			http.exceptionHandling().accessDeniedHandler(new YourAccessDeniedHandler());
			http.cors();
			return http.build();
		}
	}










