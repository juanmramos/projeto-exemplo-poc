package br.com.novaxs.crs.model.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SearchCriteriaTest {

    @Test
    public void teste_searchCriteria_200() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey("Key");
        searchCriteria.setOperation("Operation");
        searchCriteria.setValue(new Object());
        Assert.assertNotNull(searchCriteria);
        Assert.assertNotNull(searchCriteria.getKey());
        Assert.assertNotNull(searchCriteria.getOperation());
        Assert.assertNotNull(searchCriteria.getValue());
    }
}
