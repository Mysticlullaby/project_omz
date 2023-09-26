package com.omz.demo.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.omz.demo.client.dto.ClientDTO;

public class PrincipalDetails implements UserDetails {
		
//	private static final long serialVersionUID = -7062842528434366569L; // warning 처리용
	
	
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
			
//			private static final long serialVersionUID = -2102777076007588052L; // warning 처리용

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

	//계정만료여부 리턴 -  true(만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정의 잠김여부 리턴- true(잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호의 잠김여부 리턴- true(잠기지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정의 활성화 여부 리턴 - true(활성화됨)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
