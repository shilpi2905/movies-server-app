package org.codejudge.sb.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.exception.model.Error;
import org.codejudge.sb.h2.entity.EmbeddedShowId;
import org.codejudge.sb.h2.entity.MovieEntity;
import org.codejudge.sb.h2.entity.ShowEntity;
import org.codejudge.sb.h2.entity.TheatreEntity;
import org.codejudge.sb.h2.repository.MovieRepository;
import org.codejudge.sb.h2.repository.ShowRepository;
import org.codejudge.sb.h2.repository.TheatreRepository;
import org.codejudge.sb.model.input.MovieInput;
import org.codejudge.sb.model.input.ShowsInput;
import org.codejudge.sb.model.input.TheatreInput;
import org.codejudge.sb.model.output.MovieOutput;
import org.codejudge.sb.model.output.MovieShows;
import org.codejudge.sb.model.output.Shows;
import org.codejudge.sb.model.output.ShowsOutput;
import org.codejudge.sb.model.output.TheatreOutput;
import org.codejudge.sb.model.output.TheatreShows;
import org.codejudge.sb.service.MovieService;
import org.codejudge.sb.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheatreRepository theatreRepository;

	@Autowired
	private ShowRepository showRepository;

	public MovieOutput addMovie(MovieInput movie) {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setMovieName(movie.getMovieName());
		movieEntity.setMovieTrailer(movie.getMovieTrailer());
		movieEntity.setMoviePoster(movie.getMoviePoster());
		movieEntity.setMovieOverview(movie.getMovieOverview());
		movieEntity.setMovieLength(movie.getMovieLength());
		try {
			MovieEntity mov = movieRepository.save(movieEntity);
			log.info("output:: {}", movieRepository.findById(mov.getMovieId()));
			return constructMovieOutput(mov);
		} catch (Exception ex) {
			throw new CommonException(
					new Error("failure", "Movie already exist, please create movie with another name"));
		}

	}

	private MovieOutput constructMovieOutput(MovieEntity movieEntity) {
		MovieOutput output = new MovieOutput();
		output.setMovieId(movieEntity.getMovieId());
		output.setMovieName(movieEntity.getMovieName());
		output.setMovieTrailer(movieEntity.getMovieTrailer());
		output.setMoviePoster(movieEntity.getMoviePoster());
		output.setMovieOverview(movieEntity.getMovieOverview());
		output.setMovieLength(movieEntity.getMovieLength());
		return output;
	}

	private TheatreOutput constructTheatreOutput(TheatreEntity theatreEntity) {
		TheatreOutput output = new TheatreOutput();
		output.setTheatreId(theatreEntity.getTheatreId());
		output.setTheatreName(theatreEntity.getTheatreName());
		output.setTheatreLocation(theatreEntity.getTheatreLocation());
		output.setCity(theatreEntity.getCity());
		output.setPincode(theatreEntity.getPincode());
		return output;
	}

	public TheatreOutput addTheatre(TheatreInput theatre) {
		TheatreEntity theatreEntity = new TheatreEntity();
		theatreEntity.setTheatreName(theatre.getTheatreName());
		theatreEntity.setTheatreLocation(theatre.getTheatreLocation());
		theatreEntity.setCity(theatre.getCity());
		theatreEntity.setPincode(theatre.getPincode());
		TheatreEntity the = theatreRepository.save(theatreEntity);
		log.info("output:: {}", theatreRepository.findById(theatreEntity.getTheatreId()));
		return constructTheatreOutput(the);
	}

	public ShowsOutput addShow(ShowsInput show) throws ParseException {
		if (movieRepository.findById(show.getMovieId()).isPresent()
				&& theatreRepository.findById(show.getTheatreId()).isPresent()) {
			MovieEntity movie = movieRepository.findById(show.getMovieId()).get();
			Long movieLength = movie.getMovieLength();
			TheatreEntity theatre = theatreRepository.findById(show.getTheatreId()).get();
			List<ShowEntity> shows = showRepository.findByIdMovieIdAndIdTheatreId(show.getMovieId(),
					show.getTheatreId());
			if (shows.isEmpty() || !checkShowsOverlap(shows, show.getDate(), show.getTime(), movieLength)) {
				ShowEntity showEntity = new ShowEntity(
						new EmbeddedShowId(show.getMovieId(), show.getTheatreId(), show.getTime()), show.getDate());
				shows.add(showEntity);
				ShowEntity savedEnity = showRepository.save(showEntity);
				log.info("savedEnity:: {}", savedEnity);
			} else {
				throw new CommonException(new Error(Constants.FAILURE, Constants.SHOWS_OVERLAP));
			}

			ShowsOutput output = new ShowsOutput();
			output.setMovie(constructMovieOutput(movie));
			output.setTheatre(constructTheatreOutput(theatre));
			List<Shows> showsList = new ArrayList<Shows>();
			for (ShowEntity s : shows) {
				Shows sh = new Shows(s.getDate(), s.getId().getTime());
				showsList.add(sh);
			}
			output.setShows(showsList);
			return output;
		} else {
			throw new CommonException(new Error(Constants.FAILURE, Constants.MOVIE_THEATRE_NOT_EXIST));
		}
	}

	private boolean checkShowsOverlap(List<ShowEntity> shows, String showDate, String showTime, Long movieLength)
			throws ParseException {
		List<ShowEntity> filteredByDate = shows.stream().filter(s -> s.getDate().equals(showDate))
				.collect(Collectors.toList());
		SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.DATE_TIME_REGEX);
		Date convertedShowTime = timeFormat.parse(showDate + " " + showTime);
		for (ShowEntity show : filteredByDate) {
			Date time = timeFormat.parse(show.getDate() + " " + show.getId().getTime());
			Date movieEndTime = Date.from(time.toInstant().plus(movieLength, ChronoUnit.MINUTES));
			if ((convertedShowTime.after(time) || convertedShowTime.equals(time))
					&& (convertedShowTime.before(movieEndTime) || convertedShowTime.equals(movieEndTime))) {
				return true;
			}

		}
		return false;
	}

	public MovieShows getShowsByCityAndDate(Integer movieId, String city, String date) {
		if (movieRepository.findById(movieId).isPresent()) {
			MovieEntity movie = movieRepository.findById(movieId).get();
			MovieOutput mOutput = constructMovieOutput(movie);
			MovieShows mShows = new MovieShows();
			mShows.setMovie(mOutput);
			List<ShowEntity> shows = showRepository.findByIdMovieIdAndDate(movieId, date);
			if (shows.isEmpty()) {
				throw new CommonException(new Error(Constants.FAILURE, Constants.SHOWS_NOT_AVAILABLE_DATE
						+ movie.getMovieName() + Constants.SHOWS_NOT_AVAILABLE_DATE_1 + date));
			}
			List<Integer> theatreList = shows.stream().map(s -> s.getId().getTheatreId()).collect(Collectors.toList());
			List<TheatreEntity> theatres = theatreRepository.findAllById(theatreList);
			List<TheatreEntity> filteredTheatresByCity = theatres.stream()
					.filter(t -> t.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
			if (filteredTheatresByCity.isEmpty()) {
				throw new CommonException(new Error(Constants.FAILURE,
						Constants.SHOWS_NOT_AVAILABLE_LOC + movie.getMovieName() + " at " + city));
			}
			List<TheatreShows> tShows = new ArrayList<TheatreShows>();
			for (TheatreEntity entity : filteredTheatresByCity) {
				tShows.add(constructTheatreShows(entity, shows));
			}
			mShows.setTheatres(tShows);
			return mShows;

		} else {
			throw new CommonException(
					new Error(Constants.FAILURE, Constants.MOVIE_NOT_EXIST + movieId + Constants.DOES_NOT_EXIST));
		}
	}

	private TheatreShows constructTheatreShows(TheatreEntity theatre, List<ShowEntity> shows) {
		List<ShowEntity> filteredShowsByTheatre = shows.stream()
				.filter(s -> s.getId().getTheatreId() == theatre.getTheatreId()).collect(Collectors.toList());
		TheatreShows tShows = new TheatreShows();
		tShows.setTheatreId(theatre.getTheatreId());
		tShows.setTheatreName(theatre.getTheatreName());
		tShows.setCity(theatre.getCity());
		tShows.setPincode(theatre.getPincode());
		tShows.setTheatreLocation(theatre.getTheatreLocation());
		List<Shows> showsList = new ArrayList<Shows>();
		for (ShowEntity sentity : filteredShowsByTheatre) {
			Shows show = new Shows(sentity.getDate(), sentity.getId().getTime());
			showsList.add(show);
		}
		tShows.setShows(showsList);
		return tShows;
	}

}
