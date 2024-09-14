package com.dh.clinica.service;


import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.repository.IOdontologoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService{
    static Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private IOdontologoRepository iOdontologoRepository;

    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("odontologo guardado: "+ odontologo);
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        logger.info("odontologo encontrado: "+ iOdontologoRepository.findById(id));
        return iOdontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> listar() {
        return iOdontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        logger.info("odontologo " +odontologo+ " modificado");
        iOdontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        logger.info("odontologo eliminado ");
        iOdontologoRepository.deleteById(id );
    }
}
