package br.com.novaxs.crs.model.convert;

import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.model.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConvert {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toClientDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity toClientEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public Page<UserDTO> toClientDTOList(Page<UserEntity> userEntities) {
        return userEntities.map(UserDTO::new);
    }

    public List<UserDTO> toClientDTOList(List<UserEntity> userEntities) {
        return userEntities.stream().map(
                userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }
}
