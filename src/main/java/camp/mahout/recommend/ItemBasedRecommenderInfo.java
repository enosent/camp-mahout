package camp.mahout.recommend;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import camp.mahout.util.Const;

/**
 * Created by enosent on 2015. 11. 8..
 */
public class ItemBasedRecommenderInfo {
	
	public static void main(String[] args) throws Exception {

		DataModel model = new FileDataModel(new File(Const.getSampleFile()));
		
		ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
		
		ItemBasedRecommender itemBasedRecommender = new GenericBooleanPrefItemBasedRecommender(model, similarity);
		
		List<RecommendedItem> recommendations = itemBasedRecommender.mostSimilarItems(13, 3);
		
		recommendations.forEach(item -> {
			System.out.println(item.getItemID() + " (" + item.getValue() +")");
		});
	}
}
