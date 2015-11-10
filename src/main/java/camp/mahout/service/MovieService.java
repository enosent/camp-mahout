package camp.mahout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import camp.mahout.domain.Movie;
import camp.mahout.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
	
}