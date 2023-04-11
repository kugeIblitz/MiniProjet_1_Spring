package com.rayen.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rayen.film.entities.Film;
import com.rayen.film.service.FilmService;

import java.util.Date;

@SpringBootApplication
public class FilmApplication implements CommandLineRunner {
    @Autowired
    FilmService filmService;

    public static void main(String[] args) {
        SpringApplication.run(FilmApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }

}
