package com.refugio.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.refugio.web.DAO.usuarioDAO;
import com.refugio.web.entity.Usuario;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private usuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDAO.BuscarPorNombreUsuario(username);
		
		UserBuilder user = null;
		if(usuario!=null) {
			user = org.springframework.security.core.userdetails.User.withUsername(username);
			user.password(usuario.getPassword());
			user.roles("USER");
			
		}else {
			
			throw new UsernameNotFoundException("usuario no encontrado");
		}
		
		
		return user.build();
	}

}
