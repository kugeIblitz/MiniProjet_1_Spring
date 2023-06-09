package com.rayen.film.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rayen.film.entities.Film;
import com.rayen.film.service.FilmService;


@Controller
public class FilmController {
    @Autowired
    FilmService filmService;
    @RequestMapping("/showCreate")
    public String showCreate()
    {
        return "createFilm";
    }
    @RequestMapping("/saveFilm")
    public String saveFilm(@ModelAttribute("film") Film film,
                              @RequestParam("date") String date,
                              ModelMap modelMap) throws
            ParseException
    {
//conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        film.setDateCreation(dateCreation);

        Film saveFilm = filmService.saveFilm(film);
        String msg ="Film enregistré avec Id "+saveFilm.getIdFilm();
        modelMap.addAttribute("msg", msg);
        /*return "createFilm";*/
        return "redirect:/ListeFilms";
    }
    @RequestMapping("/ListeFilms")
    public String listeProduits(ModelMap modelMap,
                                @RequestParam (name="page",defaultValue = "0") int page,
                                @RequestParam (name="size", defaultValue = "4") int size)
    {
        Page<Film> prods = filmService.getAllFilmsParPage(page, size);
        modelMap.addAttribute("films", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        /*List<Film> fil = filmService.getAllFilm();
        modelMap.addAttribute("films", fil);*/
        return "listeFilms";
    }
    @RequestMapping("/supprimerFilm")
    public String supprimerFilm(@RequestParam("id") Long id,
                                   ModelMap modelMap,
                                   @RequestParam (name="page",defaultValue = "0") int page,
                                   @RequestParam (name="size", defaultValue = "4") int size)
    {
        filmService.deleteFilmById(id);
        Page<Film> prods = filmService.getAllFilmsParPage(page,
                size);
        modelMap.addAttribute("films", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeFilms";
    }
    /*@RequestMapping("/modifierFilm")
    public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Film p= filmService.getFilm(id);
        modelMap.addAttribute("produit", p);
        return "editerFilm";
    }
    @RequestMapping("/updateFilm")
    public String updateProduit(@ModelAttribute("film") Film film,
                                @RequestParam("date") String date,
    {
//conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        film.setDateCreation(dateCreation);

        filmService.updateFilm(film);
        List<Film> prods = filmService.getAllFilmsParPage();
        modelMap.addAttribute("films", prods);
        return "listeProduits";
    }*/



}
