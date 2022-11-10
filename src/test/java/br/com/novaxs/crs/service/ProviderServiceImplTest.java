package br.com.novaxs.crs.service;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.model.convert.ProviderConvert;
import br.com.novaxs.crs.model.dto.ProviderDTO;
import br.com.novaxs.crs.model.dto.SearchCriteria;
import br.com.novaxs.crs.model.entity.ProviderEntity;
import br.com.novaxs.crs.repository.ProviderRepository;
import br.com.novaxs.crs.service.impl.ProviderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProviderServiceImplTest extends BaseTest {

    @InjectMocks
    private ProviderServiceImpl providerService;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ProviderConvert providerConvert;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery criteriaQuery;

    @Mock
    private Root<ProviderEntity> providerEntityRoot;

    @Mock
    private SearchCriteria searchCriteria;

    @Before
    public void setUp() {
        searchCriteria = mock(SearchCriteria.class);
        criteriaBuilder = mock(CriteriaBuilder.class);
        criteriaQuery = mock(CriteriaQuery.class);
        providerEntityRoot = mock(Root.class);
    }

    @Test
    public void test_save_provider() {
        when(providerRepository.save(Mockito.any())).thenReturn(mockProviderEntity());
        when(providerConvert.toProviderEntity(Mockito.any())).thenReturn(mockProviderEntity());
        when(providerConvert.toProviderDTO(Mockito.any())).thenReturn(mockProviderDTO());
        ProviderDTO result = providerService.save(mockProviderDTO());
        Assert.assertNotNull(result);
    }

    @Test
    public void test_findById() {
        when(providerRepository.findById(1L)).thenReturn(Optional.of(mockProviderEntity()));
        when(providerConvert.toProviderDTO(Mockito.any())).thenReturn(mockProviderDTO());
        ProviderDTO result = providerService.findById(1L);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_findByAll() {
        Pageable paginacao = PageRequest.of(0,1);
        when(providerRepository.findAll(paginacao)).thenReturn(mockPageProviderEntity());
        when(providerConvert.toProviderDTOList(mockPageProviderEntity())).thenReturn(mockPageProviderDTO());
        when(providerConvert.toProviderDTOList((Page<ProviderEntity>) Mockito.any())).thenReturn(mockPageProviderDTO());
        Page<ProviderDTO> result = providerService.findByAll(0, 1);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_delete() {
        providerService.delete(1L);
    }

    @Test
    public void test_findByAllFilter() {
        when(providerConvert.toProviderDTOList(mockPageProviderEntity())).thenReturn(mockPageProviderDTO());
        Collection<ProviderDTO> result = providerService.findByFilter(1l, "Teste", "teste@teste.com.br", "123456", "2122277766",
                "", LocalDateTime.now(), "", "", 1L, "", "", "", "", "", "", "");
        Assert.assertNotNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void test_toPredicate() {
        Predicate result = providerService.toPredicate(providerEntityRoot, criteriaQuery, criteriaBuilder);
        Assert.assertNotNull(result);
    }
}
