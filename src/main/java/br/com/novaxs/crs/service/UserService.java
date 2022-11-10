package br.com.novaxs.crs.service;

import br.com.novaxs.crs.model.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface UserService {

    UserDTO save(UserDTO userDTO);
    UserDTO findById(Long id);
    Page<UserDTO> findByAll(int paginacao, int qtd);
    void delete(Long id);
    Collection<UserDTO> findByFilter(Long id,
                                     String name,
                                     String email,
                                     String passwordUser,
                                     String phone);

}
