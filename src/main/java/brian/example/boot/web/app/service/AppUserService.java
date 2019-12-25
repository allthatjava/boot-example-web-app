package brian.example.boot.web.app.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import brian.example.boot.web.app.domain.AppUser;
import brian.example.boot.web.app.repository.AppUserRepository;

@Service("appUserService")
public class AppUserService implements UserDetailsService
{
	private AppUserRepository repo;

	@Autowired
	public AppUserService(AppUserRepository repo) {
		this.repo = repo;
	}

	public List<AppUser> getAllAppUsers() {
		return StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public AppUser getAppUser(String userId) {
		return repo.findByUserId(userId);
	}

	public AppUser addAppUser(AppUser appUser) {
		return repo.save(appUser);
	}

	public AppUser updateAppUser(AppUser appUser) {
		return repo.save(appUser);
	}

	public void deleteAppUser(String userId) {
		repo.deleteById(userId);
	}

	/**
	 * This is for Login
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = repo.findByUserId(username);
		if( user == null )
			throw new UsernameNotFoundException(username);

		GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");

		return new User(user.getUserId(), user.getPw(), Arrays.asList(authority));
	}
}
