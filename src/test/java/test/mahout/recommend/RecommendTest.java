package test.mahout.recommend;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import camp.mahout.Application;
import camp.mahout.service.recommend.ItemBasedRecommendService;
import camp.mahout.service.recommend.UserBasedRecommendService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RecommendTest {

	@Autowired private ItemBasedRecommendService itemBasedRecommendService;
	
	@Test
	public void itemBasedTest() throws Exception {
		List<RecommendedItem> recommend = itemBasedRecommendService.recommend();
		recommend.forEach(info -> log.info("# {} ",info) );
	}

	
	@Autowired private UserBasedRecommendService userBasedRecommendService;
	
	@Test
	public void userBasedTest() throws Exception {
		List<RecommendedItem> recommend = userBasedRecommendService.recommend();
		recommend.forEach(info -> log.info("# {}", info) );
		
		recommend.forEach(System.out::println);
	}
}