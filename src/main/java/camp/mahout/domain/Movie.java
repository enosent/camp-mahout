package camp.mahout.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 2126256244511485359L;

	@Getter @Setter(AccessLevel.PRIVATE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter @Setter
	private String title;

	@Override
	public String toString() {
		return "Movie [title=" + title + "]";
	}
	
}