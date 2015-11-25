package test.mahout.recommend;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import camp.mahout.Application;
import camp.mahout.domain.Movie;
import camp.mahout.service.recommend.ItemBasedRecommendService;
import camp.mahout.service.recommend.UserBasedRecommendService;
import camp.mahout.util.Const;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RecommendTest {

	@Autowired private ItemBasedRecommendService itemBasedRecommendService;
	
	@Test
	public void itemBasedIdTest() throws Exception {
		long itemID = 13l;
		
		List<RecommendedItem> recommend = itemBasedRecommendService.recommendId(itemID);
		recommend.forEach(info -> log.info("# {} ",info) );
	}
	
	@Test
	public void itemBasedGetMovieTest() throws Exception {
		long itemID = 13l;
		
		List<Movie> recommend = itemBasedRecommendService.recommendItem(itemID);
		recommend.forEach(info -> log.info("# {} ",info) );
	}

	@Test(expected=NumberFormatException.class)
	public void failTest() throws Exception {

		DataModel model = new FileDataModel(new File(Const.getUserKeywordFile()));
		
		model.getNumItems();
	}
	
	
	@Autowired private UserBasedRecommendService userBasedRecommendService;
	
	@Test
	public void userBasedTest() throws Exception {
		List<RecommendedItem> recommend = userBasedRecommendService.recommend();
		recommend.forEach(info -> log.info("# {}", info) );
	}
	
}