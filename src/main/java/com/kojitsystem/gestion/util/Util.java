package com.kojitsystem.gestion.util;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kojitsystem.gestion.model.User;

//@Slf4j
public class Util {

	public static Optional<User> currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
			return Optional.empty();
		}
		return Optional.of((User) authentication.getPrincipal());
	}

	public static boolean hasAnyAuthority(String... authorities) {
		boolean ret = Arrays.stream(authorities).anyMatch(authority -> SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(authority)));
		System.out.println(String.format("checking current user \"%s\" authority againest %s, result: %s", currentUser().get().getUsername(),
				Arrays.toString(authorities), ret));
		return ret;
	}
}