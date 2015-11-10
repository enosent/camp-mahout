package camp.mahout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import camp.mahout.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}