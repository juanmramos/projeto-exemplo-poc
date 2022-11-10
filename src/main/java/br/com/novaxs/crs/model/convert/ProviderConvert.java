package br.com.novaxs.crs.model.convert;

import br.com.novaxs.crs.model.dto.AddressDTO;
import br.com.novaxs.crs.model.dto.ProviderDTO;
import br.com.novaxs.crs.model.entity.ProviderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderConvert {

    @Autowired
    private ModelMapper modelMapper;

    public ProviderDTO toProviderDTO(ProviderEntity providerEntity) {
        AddressDTO addressDTO = modelMapper.map(providerEntity.getAddressEntity(), AddressDTO.class);
        ProviderDTO providerDTO = modelMapper.map(providerEntity, ProviderDTO.class);
        providerDTO.setAddressDTO(addressDTO);
        return providerDTO;
    }

    public ProviderEntity toProviderEntity(ProviderDTO providerDTO) {
        br.com.novaxs.crs.model.entity.AddressEntity addressEntity = modelMapper.map(providerDTO.getAddressDTO(), br.com.novaxs.crs.model.entity.AddressEntity.class);
        ProviderEntity providerEntity = modelMapper.map(providerDTO, ProviderEntity.class);
        providerEntity.setAddressEntity(addressEntity);
        return providerEntity;
    }

    public Page<ProviderDTO> toProviderDTOList(Page<ProviderEntity> userEntities) {
        return userEntities.map(ProviderDTO::new);
    }

    public List<ProviderDTO> toProviderDTOList(List<ProviderEntity> providerEntities) {
        return providerEntities.stream().map(
                        providerEntity -> modelMapper.map(providerEntity, ProviderDTO.class))
                .collect(Collectors.toList());
    }
}
