package camp.mahout.service.recommend;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import camp.mahout.util.Const;
import camp.mahout.util.mahout.IDGenerator;
import camp.mahout.util.mahout.IDMap;
import camp.mahout.util.mahout.StringFileDataModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemBasedRecommendService {

	public List<String> recommend(String keyword) throws Exception {

		DataModel model = new StringFileDataModel(new File(Const.getUserKeywordFile()));
		IDMap idMap = new IDMap(((StringFileDataModel)model).getIndexedMapInstansce()); // 파일에서 읽어온 데이터 변환 저장정보

		long itemID = toLongID(keyword);
		log.info("# {} -> {}", keyword, itemID);

		ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
		ItemBasedRecommender itemBasedRecommender = new GenericBooleanPrefItemBasedRecommender(model, similarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.mostSimilarItems(itemID, 3);
		
		log.info("item recommend size - {}", recommendations.size());
		
		List<String> results = recommendations.stream().map(info -> {
			return idMap.getData(info.getItemID());
		}).collect(Collectors.toList());

		return results;
	}

	// String -> long 변환
	private long toLongID(String keyword) {
		IDGenerator longID = new IDGenerator(); 
		long userID = longID.toLongID(keyword);
		return userID;
	}
	
}