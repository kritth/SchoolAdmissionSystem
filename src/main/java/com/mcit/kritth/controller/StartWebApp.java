/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcit.kritth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;
/**
 *
 * @author test
 */
@Controller
public class StartWebApp
{
	@RequestMapping("/")
    public ModelAndView init()
    {		
		ModelAndView model = new ModelAndView("index");
		model.addObject("attempt", new User());
		model.addObject("register_person", new Person());
        return model;
    }
}
