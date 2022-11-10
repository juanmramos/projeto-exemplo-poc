package br.com.novaxs.crs.service;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.model.convert.UserConvert;
import br.com.novaxs.crs.model.dto.SearchCriteria;
import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.model.entity.UserEntity;
import br.com.novaxs.crs.repository.UserRepository;
import br.com.novaxs.crs.service.impl.UserServiceImpl;
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
import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplTest extends BaseTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConvert userConvert;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery criteriaQuery;

    @Mock
    private Root<UserEntity> userEntityRoot;

    @Mock
    private SearchCriteria searchCriteria;

    @Before
    public void setUp() {
        searchCriteria = mock(SearchCriteria.class);
        criteriaBuilder = mock(CriteriaBuilder.class);
        criteriaQuery = mock(CriteriaQuery.class);
        userEntityRoot = mock(Root.class);
    }

    @Test
    public void test_save_user() {
        when(userRepository.save(Mockito.any())).thenReturn(mockUserEntity());
        when(userConvert.toClientEntity(mockUserDTO())).thenReturn(mockUserEntity());
        when(userConvert.toClientDTO(mockUserEntity())).thenReturn(mockUserDTO());
        UserDTO result = userService.save(mockUserDTO());
        Assert.assertNotNull(result);
    }

    @Test
    public void test_findById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUserEntity()));
        when(userConvert.toClientDTO(mockUserEntity())).thenReturn(mockUserDTO());
        UserDTO result = userService.findById(1L);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_findByAll() {
        Pageable paginacao = PageRequest.of(0,1);
        when(userRepository.findAll(paginacao)).thenReturn(mockPageUserEntity());
        when(userConvert.toClientDTOList(mockPageUserEntity())).thenReturn(mockPageUserDTO());
        Page<UserDTO> result = userService.findByAll(0, 1);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_delete() {
        userService.delete(1L);
    }

    @Test
    public void test_findByAllFilter() {
        when(userConvert.toClientDTOList(mockPageUserEntity())).thenReturn(mockPageUserDTO());
        Collection<UserDTO> result = userService.findByFilter(1l, "Teste", "teste@teste.com.br", "123456", "2122277766");
        Assert.assertNotNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void test_toPredicate() {
        when(userConvert.toClientDTOList(mockPageUserEntity())).thenReturn(mockPageUserDTO());
        Predicate result = userService.toPredicate(userEntityRoot, criteriaQuery, criteriaBuilder);
        Assert.assertNotNull(result);
    }
}
