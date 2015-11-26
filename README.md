# camp-mahout
apache-mahout test project

##### 개발환경
+ java 1.8 + spring-boot-starter-data-jpa + apache Mahout + lombok
+ hsqldb

##### Branch 정보
+ master : 기본 추천시스템 브랜치
+ memory-id-migrator : Mahout에서 제공하는 MemoryIDMigrator를 이용한 연관(추천)검색 기능 테스트 브랜치
+ mysql-id-migrator : String -> Long 변환을 memory가 아닌 DB(mysql) 연동 테스트 브랜치

```
package : camp.mahout.service.recommend

ItemBasedRecommendService : 아이템 기반 추천 서비스
UserBasedRecommendService : 사용자 기반 추천 서비스 ( 피어슨 상관 계수를 이용한 추천 )
```

##### Sample Data
```
path : src/main/resources

u.data : 기본 예제 ( 사용자 ID, 아이템 ID, 추천수)
import.sql : 아이템 ID에 해당하는 영화 제목 샘플 데이터

user.data : 연관(추천)검색 테스트를 위한 예제 ( 사용자 ID, 키워드, 클릭수 )
```

##### Slide Link
+ [http://slides.com/ilhyunseo/deck](http://slides.com/ilhyunseo/deck)
