package com.projetointegradorgrupo3.ProjetoReintegrar.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Usuario;
import com.projetointegradorgrupo3.ProjetoReintegrar.model.UsuarioLogin;
import com.projetointegradorgrupo3.ProjetoReintegrar.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository ;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha()) ;
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario) ;
		
		
	}
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))) ;
				String authHearder = "Basic " + new String(encodeAuth) ;
				
				user.get().setToken(authHearder);
				user.get().setNome(usuario.get().getNome());
				user.get().setEmail(usuario.get().getEmail());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setFoto(usuario.get().getFoto());
//				user.get().setDataNascimento(usuario.get().getDataNascimento());
				user.get().setGenero(usuario.get().getGenero());
				user.get().setTelefone(usuario.get().getTelefone());
				user.get().setId(usuario.get().getId());
				user.get().setTipo(usuario.get().getTipo());
				
				return user ;
			}
		}
		return null;
	}
}
