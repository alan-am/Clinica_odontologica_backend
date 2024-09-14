package com.dh.clinica.service;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.repository.ITurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private ITurnoRepository turnoRepository;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    public TurnoService(ITurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    @Override
    public Turno guardarTurno(Turno turno) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo>  odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        Turno turnoARetornar = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turnoARetornar = turnoRepository.save(turno);
        }
        return turnoARetornar;
    }

    @Override
    public Optional<Turno> buscarPorId(Integer id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    @Override
    public void modificarTurno(Turno turno) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (paciente.isPresent() && odontologo.isPresent()) {
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            // se persiste el turno
            turnoRepository.save(turno);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }
}
