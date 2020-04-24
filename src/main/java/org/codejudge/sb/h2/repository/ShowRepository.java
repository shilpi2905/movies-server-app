package org.codejudge.sb.h2.repository;

import java.util.List;

import org.codejudge.sb.h2.entity.EmbeddedShowId;
import org.codejudge.sb.h2.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, EmbeddedShowId>{
	
	List<ShowEntity> findByIdMovieIdAndIdTheatreId(Integer movieId, Integer theatreId);
	
	List<ShowEntity> findByIdMovieIdAndDate(Integer movieId, String date);
	
}
