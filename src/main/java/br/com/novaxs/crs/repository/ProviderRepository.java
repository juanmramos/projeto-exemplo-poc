package br.com.novaxs.crs.repository;

import br.com.novaxs.crs.model.entity.ProviderEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
    List<ProviderEntity> findAll(Specification<ProviderEntity> providerEntitySpecification);
}
