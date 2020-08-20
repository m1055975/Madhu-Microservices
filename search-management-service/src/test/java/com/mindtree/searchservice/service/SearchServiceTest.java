package com.mindtree.searchservice.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.mindtree.searchservice.dao.SearchDao;
import com.mindtree.searchservice.service.impl.SearchServiceImplementation;

@SpringBootTest
public class SearchServiceTest {
	
	
	@InjectMocks
	private SearchServiceImplementation searchServiceImplementation;
	
	@Mock
    private SearchDao searchDao;


	@Test
	public void testAddFoodToRestaurant() {
		
	}



}
