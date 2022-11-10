package br.com.novaxs.crs.service.impl;

import br.com.novaxs.crs.model.convert.ProviderConvert;
import br.com.novaxs.crs.model.dto.ProviderDTO;
import br.com.novaxs.crs.model.dto.SearchCriteria;
import br.com.novaxs.crs.model.entity.ProviderEntity;
import br.com.novaxs.crs.repository.ProviderRepository;
import br.com.novaxs.crs.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService, Specification<ProviderEntity> {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderConvert providerConvert;

    private SearchCriteria criteria;

    @Override
    public ProviderDTO save(ProviderDTO providerDTO) {
        ProviderEntity providerEntity = providerConvert.toProviderEntity(providerDTO);
        ProviderEntity provider = providerRepository.save(providerEntity);
        return providerConvert.toProviderDTO(provider);
    }

    @Override
    public ProviderDTO findById(Long id) {
        ProviderEntity providerEntity = providerRepository.findById(id).get();
        return providerConvert.toProviderDTO(providerEntity);
    }

    @Override
    public Page<ProviderDTO> findByAll(int pagina, int qtd) {
        Pageable paginacao = PageRequest.of(pagina,qtd);
        Page<ProviderEntity> providerEntities = providerRepository.findAll(paginacao);
        return providerConvert.toProviderDTOList(providerEntities);
    }

    @Override
    public void delete(Long id) {
        providerRepository.deleteById(id);
    }

    @Override
    public Collection<ProviderDTO> findByFilter(Long id,
                                                String firstName,
                                                String lastName,
                                                String email,
                                                String phoneNumber,
                                                String taxPayerId,
                                                LocalDateTime birthDate,
                                                String statementDescriptor,
                                                String mcc,
                                                Long idAddress,
                                                String postalCode,
                                                String line1,
                                                String line2,
                                                String neighborhood,
                                                String city,
                                                String state,
                                                String country_code) {
        List<ProviderEntity> providerEntities = providerRepository.findAll(new Specification<ProviderEntity>() {
            @Override
            public Predicate toPredicate(Root<ProviderEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (id != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), id)));
                }
                if (firstName != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), firstName)));
                }
                if (lastName != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), lastName)));
                }
                if (email != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email)));
                }
                if (phoneNumber != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber)));
                }
                if (taxPayerId != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("taxPayerId"), taxPayerId)));
                }
                if (birthDate != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("birthDate"), birthDate)));
                }
                if (statementDescriptor != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("statementDescriptor"), statementDescriptor)));
                }
                if (mcc != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("mcc"), mcc)));
                }
                if (idAddress != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("idAddress"), idAddress)));
                }
                if (postalCode != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("postalCode"), postalCode)));
                }
                if (line1 != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("line1"), line1)));
                }
                if (line2 != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("line2"), line2)));
                }
                if (neighborhood != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("neighborhood"), neighborhood)));
                }
                if (city != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("city"), city)));
                }
                if (state != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), state)));
                }
                if (country_code != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("country_code"), country_code)));
                }
                return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[predicates.size()])));
            }
        });

        return providerConvert.toProviderDTOList(providerEntities);
    }

    @Override
    public Specification<ProviderEntity> and(Specification<ProviderEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<ProviderEntity> or(Specification<ProviderEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<ProviderEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
