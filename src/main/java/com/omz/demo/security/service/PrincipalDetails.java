package com.omz.demo.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.omz.demo.client.dto.ClientDTO;

public class PrincipalDetails implements UserDetails {
	
	private ClientDTO clientDTO;
	
	public PrincipalDetails() {		
	}
	
	public PrincipalDetails(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
	}
	
	public ClientDTO getClientDTO() {
		return clientDTO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {


		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return clientDTO.getAuthRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return clientDTO.getClientPass();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return clientDTO.getClientId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
