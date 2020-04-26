DROP TABLE IF EXISTS MOVIES;
DROP TABLE IF EXISTS THEATRES;
DROP TABLE IF EXISTS SHOWS;
 
CREATE TABLE MOVIES (
  movie_id INT AUTO_INCREMENT  PRIMARY KEY,
  movie_name VARCHAR(200) NOT NULL UNIQUE,
  movie_trailer VARCHAR(250) NOT NULL,
  movie_overview VARCHAR(1000) NOT NULL,
  movie_poster VARCHAR(250) NOT NULL,
  length INT NOT NULL
);

CREATE TABLE THEATRES (
  theatre_id INT AUTO_INCREMENT  PRIMARY KEY,
  theatre_name VARCHAR(200) NOT NULL,
  theatre_location VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  pincode INT NOT NULL
);

CREATE TABLE SHOWS (
  movie_id INT NOT NULL,
  theatre_id INT NOT NULL,
  movie_time VARCHAR(250) NOT NULL,
  movie_date VARCHAR(250) NOT NULL,
  CONSTRAINT id PRIMARY KEY (movie_id, theatre_id, movie_time)
);