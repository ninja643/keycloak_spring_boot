package rs.stankovicsoft.keycloak_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
	@Bean
	public SecurityFilterChain configure(final HttpSecurity http) throws Exception
	{
		http.oauth2Client(httpSecurityOAuth2ClientConfigurer -> {})
			.oauth2Login(configurer -> configurer.tokenEndpoint(tokenEndpoint -> {}).userInfoEndpoint(userInfoEndpoint -> {}));

		http.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

		http.authorizeHttpRequests(requests -> requests
			.requestMatchers("/unauthenticated", "/oauth2/**", "/login/**").permitAll()
			.anyRequest().fullyAuthenticated());

		http.logout(logout -> logout.logoutSuccessUrl(
			"http://localhost:8080/realms/external/protocol/openid-connect/logout?redirect_uri=http://localhost:8081/"));

		return http.build();
	}
}
