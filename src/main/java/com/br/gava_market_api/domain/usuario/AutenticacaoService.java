package com.br.gava_market_api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository respository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return respository.findByLogin(username);
	}
	
	
}
