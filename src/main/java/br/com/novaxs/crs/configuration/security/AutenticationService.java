package br.com.novaxs.crs.configuration.security;

import br.com.novaxs.crs.model.entity.UserEntity;
import br.com.novaxs.crs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
