package korol.miskaris.laba2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import korol.miskaris.laba2.model.User;
import korol.miskaris.laba2.repos.UserRepository;
import korol.miskaris.laba2.security.UsersDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {
	@Autowired
	private final UserRepository userRepos;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional <User> user = userRepos.findByUsername(username);
		if (user.isEmpty())
			throw new UsernameNotFoundException("User " + username + " is not found");
		return new UsersDetails(user.get());
	}
}
