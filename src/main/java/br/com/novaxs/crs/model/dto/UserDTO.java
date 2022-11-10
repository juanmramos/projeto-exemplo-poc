package br.com.novaxs.crs.model.dto;

import br.com.novaxs.crs.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String passwordUser;
    private String phone;

    private String cpf;

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.passwordUser = userEntity.getPasswordUser();
        this.phone = userEntity.getPhone();
        this.cpf = userEntity.getCpf();
    }

}
