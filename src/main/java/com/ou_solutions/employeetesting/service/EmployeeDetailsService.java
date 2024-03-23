package com.ou_solutions.employeetesting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ou_solutions.employeetesting.entity.Employee;
import com.ou_solutions.employeetesting.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {
	
	private final EmployeeRepository empRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(empRepository.findByEmail(username).isPresent())
		{
			
			Employee employee = empRepository.findByEmail(username).get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			
			return new User(employee.getEmail(), employee.getPassword(), authorities);
		}
		else {
			throw new UsernameNotFoundException("User Not found");
		}
		
		
	}

}
