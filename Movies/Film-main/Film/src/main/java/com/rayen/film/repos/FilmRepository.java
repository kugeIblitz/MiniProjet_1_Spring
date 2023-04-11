package com.rayen.film.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayen.film.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
