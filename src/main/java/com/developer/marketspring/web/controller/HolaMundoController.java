package com.developer.marketspring.web.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/saludar")
public class HolaMundoController {

    @GetMapping("/hola")
    public String saludar(){
        return " Nunca paremos de aprender ! 👩‍🚀 ";
    }


    //@Scheduled(cron = "*/10 * * * * *", zone = "America/Bogota") // cada 10 segundos
    // "segundos, minutos, horas, dia mes, mes, dia semana,
    // el uso de ? se usa para cada dia, mes, año"
    @Scheduled(cron = "0 30 22 * * *", zone = "America/Bogota")
    public void despertar() {
        System.out.println("¡Buenos días!");
        System.out.println("DESPIERTAAAAAAAA");
    }
}
