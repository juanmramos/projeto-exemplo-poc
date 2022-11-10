package br.com.novaxs.crs.service.impl;

import br.com.novaxs.crs.model.convert.UserConvert;
import br.com.novaxs.crs.model.dto.SearchCriteria;
import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.model.entity.UserEntity;
import br.com.novaxs.crs.repository.UserRepository;
import br.com.novaxs.crs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, Specification<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvert userConvert;

    private SearchCriteria criteria;

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = userConvert.toClientEntity(userDTO);
        String password = enconde(userEntity.getPasswordUser());
        userEntity.setPasswordUser(password);
        UserEntity userEntity1 = userRepository.save(userEntity);
        return userConvert.toClientDTO(userEntity1);
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        return userConvert.toClientDTO(userEntity);
    }

    @Override
    public Page<UserDTO> findByAll(int pagina, int qtd) {
        Pageable paginacao = PageRequest.of(pagina,qtd);
        Page<UserEntity> userEntityPage = userRepository.findAll(paginacao);
        return userConvert.toClientDTOList(userEntityPage);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Collection<UserDTO> findByFilter(Long id, String name, String email, String passwordUser, String phone) {
        List<UserEntity> userEntities = userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (id != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), id)));
                }
                if (name != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), name)));
                }
                if (email != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email)));
                }
                if (passwordUser != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("passwordUser"), passwordUser)));
                }
                if (phone != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("phone"), phone)));
                }
                return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[predicates.size()])));
            }
        });

        return userConvert.toClientDTOList(userEntities);
    }

    private String enconde(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Specification<UserEntity> and(Specification<UserEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<UserEntity> or(Specification<UserEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
