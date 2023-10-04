package com.omz.demo.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.omz.demo.client.dto.ClientDTO;

//시큐리티가 /login 주소 요청이 오면 중간에서 낚아채서(intercept) 로그인을 진행시킴
//로그인 진행이 완료되면 여러 session 중에서 security 만의 독자적인 session을 만들어줌(Security ContextHolder)
//해당 세션에 들어갈 수 있는 오브젝트 타입은 Authentication 타입 객체임 (Authentication 객체 안에 user 정보가 있어야 함)
//user 오브젝트 타입은 userDetails 타입 객체로 넣도록 클래스가 지정되어 있음
//Security Session에 세션정보를 저장해주는데 들어갈 수 있는 객체는 Authentication에 User 정보가 들어가는게 UserDetails 타입임
//Security Session에 있는 세션 정보를 꺼내면 Authentication 객체가 나오고 Authentication에서 UserDetails 객체를 꺼내면 UserObject에 접근가능
//그렇기 떄문에 해당 객체를 꺼내려면 UserDetails 상속받고 PrincipalDetailesService가 UserDetails와 같은 타입이 되면서 Authentication에 넣을 수 있게 됨

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

	// 해당 user의 권한을 리턴하는 곳
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
