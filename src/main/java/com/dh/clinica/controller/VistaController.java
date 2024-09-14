package com.dh.clinica.controller;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaController {

    private PacienteService pacienteService; //para poder utilizar los metodos de esta clase

    public VistaController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // localhost:8080/1 -> @PathVariable
    // localhost:8080?id=1 @RequestParams
    @GetMapping("/index")
    public String mostrarPacientePorId(Model model, @RequestParam Integer id){ //debo indicarque como voy a recibir la info
        Paciente paciente = pacienteService.buscarPorId(id).get();//get para obtener el objeto del Optional
        model.addAttribute("nombrePaciente", paciente.getNombre());
                            // nombrede la variable definida, atributo a traves del metodo get
        model.addAttribute("apellidoPaciente", paciente.getApellido());
        return "paciente";

    }
}
