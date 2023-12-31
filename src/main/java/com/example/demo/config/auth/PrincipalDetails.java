package com.example.demo.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.example.demo.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class PrincipalDetails extends Member implements UserDetails{

	private final Member member;

    public PrincipalDetails(Member member){
        this.member = member;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(member::getRoles);
        return authorities;
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        member.getRoleList().forEach(r -> authorities.add(() -> r));
//        return authorities;
    }
}
