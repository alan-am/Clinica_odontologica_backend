package com.dh.clinica.controller;


import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //1 indicamos un controlador
@RequestMapping("/paciente") //2 indicamos la ruta del endpoint
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //3 realizamos el 'crud'
    @PostMapping("/guardar")
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);

    }

    @GetMapping("/buscar/{id}")
    public Paciente buscarPorId(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }

    

}
