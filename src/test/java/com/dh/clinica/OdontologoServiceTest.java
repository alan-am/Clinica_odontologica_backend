package com.dh.clinica;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;

    Odontologo odontologo;
    Odontologo odontologoDesdeDb;

    @BeforeEach
    void crearOdontologo(){
        odontologo = new Odontologo();
        odontologo.setMatricula(8968);
        odontologo.setNombre("Luciana");
        odontologo.setApellido("Romero");

        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologo);
    }



    @Test
    @DisplayName("Testear que un Odontologo se guarde en la base de datos con su domicilio")
    void caso1(){
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un Odontologo pueda ser obtenido cuando se envia el id")
    void caso2(){
        //dado
        Integer id = odontologoDesdeDb.getId();
        // cuando
        Odontologo pacienteEncontrado = odontologoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, pacienteEncontrado.getId());
    }

}