package com.kojitsystem.gestion.model;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@Table(name = "\"t_User\"")
public class User extends BaseModel implements Serializable, UserDetails  {
	@JsonIgnore
	private static final long serialVersionUID = 6534751544232619891L;
	public static enum Role {
		ROLE_ADMIN, ROLE_USER
	}
	@Column(unique=true, nullable = false)
	private String username;
    @Column(unique=true, nullable = false)
    private String email;
    @JsonProperty(access=Access.WRITE_ONLY)
    private String password;
	private boolean enabled = true;
	@Enumerated(EnumType.STRING)
	private Role role = Role.ROLE_USER;
	
    public User() {}
    
    public User(String username, String email, String password, Role role) {
    	super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public void setPassword(String password) {
        this.password = password;
    }

	public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonIgnore
	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.name()));
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}