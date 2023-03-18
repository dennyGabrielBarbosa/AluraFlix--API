package com.challenge.videos.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challenge.videos.modelo.Usuario;
import com.challenge.videos.repository.UsuarioRepository;

@Service
@Transactional
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String nomeDeUsuario) throws UsernameNotFoundException {
		Usuario usuarioModelo = usuarioRepository.findByNomeDeUsuario(nomeDeUsuario)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado" + nomeDeUsuario));
		return new User(usuarioModelo.getNomeDeUsuario(), usuarioModelo.getSenha(), true, true, true,true, usuarioModelo.getAuthorities());
	}

}
