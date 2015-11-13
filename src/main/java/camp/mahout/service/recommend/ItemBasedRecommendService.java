package camp.mahout.service.recommend;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import camp.mahout.domain.Movie;
import camp.mahout.repository.MovieRepository;
import camp.mahout.util.Const;

@Service
public class ItemBasedRecommendService {
	
	@Autowired
	private MovieRepository repository;

	public List<Movie> recommend() throws Exception {

		DataModel model = new FileDataModel(new File(Const.getSampleFile()));

		ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
		ItemBasedRecommender itemBasedRecommender = new GenericBooleanPrefItemBasedRecommender(model, similarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.mostSimilarItems(13, 3);
		
		List<Movie> results = recommendations.stream().map(info -> {
			return repository.findOne(info.getItemID());
		}).collect(Collectors.toList());

		return results;
	}
}