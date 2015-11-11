package camp.mahout;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import camp.mahout.domain.Movie;
import camp.mahout.service.MovieService;

/**
 * Created by enosent on 2015. 11. 7..
 */
@SpringBootApplication
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(MovieService movieService) {
    	return (args -> {
    		logger.info("### connection test.");

    		List<Movie> results = movieService.findAll();
    		
    		IntStream.range(0, 5).forEach(idx -> { logger.info("# {}", results.get(idx).getTitle()); });
    	});
    }
}