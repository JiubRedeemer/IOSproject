package com.example.help.controller;

import com.example.help.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class WebController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        String sql = "SELECT * FROM user_auth WHERE username LIKE '" +name+"'";
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return users.get(0);
    }

    @GetMapping(value = "/login")
    public String login(){

        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
        if(authenticator == null || authenticator instanceof AnonymousAuthenticationToken){
            return "/login";
        }
        return "redirect:/";
    }

    @GetMapping(value ="/personal.html")
    public String personal(@RequestParam(name = "cafedre", required = false, defaultValue = "TEST") String cafedre,
                           @RequestParam(name = "email", required = false, defaultValue = "TEST") String email,
                           @RequestParam(name = "course", required = false, defaultValue = "TEST") String course,
                           @RequestParam(name = "direction", required = false, defaultValue = "TEST") String direction,
                           @RequestParam(name = "institute", required = false, defaultValue = "TEST") String institute,
                           @RequestParam(name = "fio", required = false, defaultValue = "TEST") String fio, Model model){
        email = ((User)getCurrentUser()).getEmail();
        cafedre = (((User) getCurrentUser()).getCafedre());
        course = ((User)getCurrentUser()).getCourse();
        direction = ((User)getCurrentUser()).getDirection();
        institute = ((User)getCurrentUser()).getInstitute();
        fio = ((User)getCurrentUser()).getSurname() + " " + ((User)getCurrentUser()).getName() + " " + ((User)getCurrentUser()).getMiddlename();
        model.addAttribute("fio", fio);
        model.addAttribute("email", email);
        model.addAttribute("course", course);
        model.addAttribute("direction", direction);
        model.addAttribute("institute", institute);
        model.addAttribute("cafedre", cafedre);

        return "/personal.html";
    }

    @GetMapping(value ="/myplan.html")
    public String myPlan(@RequestParam(name = "fio", required = false, defaultValue = "TEST") String fio, Model model){
        fio = ((User)getCurrentUser()).getSurname() + " " + ((User)getCurrentUser()).getName() + " " + ((User)getCurrentUser()).getMiddlename();
        model.addAttribute("fio", fio);
        return "/myplan.html";
    }

    @GetMapping(value ="/object.html")
    public String object(){
        return "/object.html";
    }

    @GetMapping(value ="/timetable.html")
    public String timetable(@RequestParam(name = "fio", required = false, defaultValue = "TEST") String fio, Model model){
        fio = ((User)getCurrentUser()).getSurname() + " " + ((User)getCurrentUser()).getName() + " " + ((User)getCurrentUser()).getMiddlename();
        model.addAttribute("fio", fio);
        return "/timetable.html";
    }

    @GetMapping(value ="")
    public String homePage(@RequestParam(name = "fio", required = false, defaultValue = "TEST") String fio, Model model){
        fio = ((User)getCurrentUser()).getSurname() + " " + ((User)getCurrentUser()).getName() + " " + ((User)getCurrentUser()).getMiddlename();
        model.addAttribute("fio", fio);
        return "/homepage";
    }

    @GetMapping(value ="homepage.html")
    public String homePage1(@RequestParam(name = "fio", required = false, defaultValue = "TEST") String fio, Model model){
        fio = ((User)getCurrentUser()).getSurname() + " " + ((User)getCurrentUser()).getName() + " " + ((User)getCurrentUser()).getMiddlename();
        model.addAttribute("fio", fio);
        return "/homepage";
    }
}
