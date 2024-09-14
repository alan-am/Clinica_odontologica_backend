package com.dh.clinica.service;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.repository.IPacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //agregado
public class PacienteService implements IPacienteService{
    static Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("Paciente guardado: "+ paciente);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        logger.info("Paciente encontrado: "+ pacienteRepository.findById(id));
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        logger.info("Paciente " +paciente+ " modificado");
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        logger.info("Paciente eliminado ");
        pacienteRepository.deleteById(id);
    }
}