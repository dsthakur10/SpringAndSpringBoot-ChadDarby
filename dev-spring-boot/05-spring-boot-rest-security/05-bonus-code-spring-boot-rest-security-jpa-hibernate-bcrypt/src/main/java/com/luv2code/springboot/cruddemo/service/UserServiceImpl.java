package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.RoleDao;
import com.luv2code.springboot.cruddemo.dao.UserDao;
import com.luv2code.springboot.cruddemo.entity.User;
import com.luv2code.springboot.cruddemo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	private RoleDao roleDao;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}


/*

loadUserByUsername() is part of Spring Security & method of Interface "UserDetailsService" &
is used to load user details based on a username.

It takes a username as a parameter and should return a UserDetails object, which is used for
user authentication.

In this method:
	It calls the findByUserName method to fetch the user details by the given username.
	If the user is not found, it throws a UsernameNotFoundException, indicating that username is invalid.
	If the user is found, it creates & returns "org.springframework.security.core.userdetails.User" object,
	which is a Spring Security class representing a user with a username, password, and
	collection of authorities (or roles). The username and password are retrieved from the user entity,
	and the authorities are obtained by calling the mapRolesToAuthorities() method, which maps the user's
	roles to security authorities.

*/

/*

private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)

	It takes a collection of Role objects as its parameter and returns a collection of
	GrantedAuthority objects.

	roles.stream(): This line starts by creating a stream of the Role objects in the input collection.
	A stream allows you to process elements in a functional and concise way.

	.map(role -> new SimpleGrantedAuthority(role.getName())):
	--> Within the stream, the map operation is applied to each Role object.
	--> For each Role in the collection, it creates a new SimpleGrantedAuthority object.
	--> The SimpleGrantedAuthority class is provided by Spring Security and represents an authority
		(or role).
	--> The constructor of SimpleGrantedAuthority takes a single argument, which is the name of the
		authority. In this case, it uses the getName() method of the Role object to get the role's name,
		and this name is used as the authority name.
	--> So, it effectively maps each Role object to a GrantedAuthority object.

	.collect(Collectors.toList()):
	--> After mapping each Role to a GrantedAuthority, the collect operation is used to gather
		these GrantedAuthority objects into a List.
	--> This is the final result of the mapRolesToAuthorities method.

SUMMARY:
In summary, the mapRolesToAuthorities method takes a collection of Role objects,
and for each Role, it converts it into a SimpleGrantedAuthority object using the role's name.
It then collects these GrantedAuthority objects into a list and returns the list as a
collection of authorities

*/