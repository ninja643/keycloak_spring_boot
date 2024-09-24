package rs.stankovicsoft.keycloak_spring_boot.controller;

import java.util.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController
{
	@GetMapping("/")
	public Map<String, String> index()
	{
		final OAuth2User user = (OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		final String name = user.getAttribute("name");
		final String email = user.getAttribute("email");
		return Map.of(
			"hello", name != null ? name : "Unknown",
			"email", email != null ? email : "Unknown email");
	}

	@GetMapping("/unauthenticated")
	public String unauthenticated()
	{
		return "unauthenticated";
	}
}
