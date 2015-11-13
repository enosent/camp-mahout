package camp.mahout.service.recommend;

import java.io.File;
import java.util.List;

//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import camp.mahout.util.Const;
import camp.mahout.util.mahout.StringFileDataModel;

@Service
public class ItemBasedRecommendService {

	public List<RecommendedItem> recommend() throws Exception {

		DataModel model = new StringFileDataModel(new File(Const.getSampleFile()));

		/*
		 * 기존 Long ID 사용이유 -> 적은 메모리사용을 위해 
		 * 
		 * String 값을 사용시 Long Type Id Conversation Exception / Type Miss Match Exception
		 * 
		 * MemoryIDMigrator 를 이용해 String 값을 Long 값으로 변환 필요
		 *
		 * hash256을 이용한 Random한 Long 값 생성 후 FlatIDMap에 저장
		 * 
		 * FlatIDMap 기존 Map을 커스터마이징한 것으로 Java Collection의 성능적 한계를 개선
		 */

		ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
		ItemBasedRecommender itemBasedRecommender = new GenericBooleanPrefItemBasedRecommender(model, similarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.mostSimilarItems(13, 3);

		return recommendations;
	}
}