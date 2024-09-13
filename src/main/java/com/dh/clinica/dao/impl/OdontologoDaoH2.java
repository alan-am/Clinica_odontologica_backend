package com.dh.clinica.dao.impl;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.db.H2Connection;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {
    public static final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);
    public static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES(DEFAULT,?,?,?);";
    public static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    public static final String SELECT_ID ="SELECT * FROM ODONTOLOGOS WHERE ID = ?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Odontologo odontologoARetornar =  null;
        Connection conexion = null;

        try{
            conexion = H2Connection.getConnection();
            logger.info("Se ha establecido conexion con la DB H2 para guardar");
            conexion.setAutoCommit(false);
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            sentenciaPreparada.setInt(1, odontologo.getMatricula());
            sentenciaPreparada.setString(2, odontologo.getNombre());
            sentenciaPreparada.setString(3, odontologo.getApellido());
            sentenciaPreparada.execute();
            conexion.commit();
            logger.info("Los cambios han sido ejecutados con exito");

            //Devolver datos
            ResultSet tablaDatos = sentenciaPreparada.getGeneratedKeys();
            Integer id = null;
            if(tablaDatos.next()){
                id = tablaDatos.getInt(1);
            }

            odontologoARetornar = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());

            //Mostrar datos
            logger.info("Odontologo/a guardado correctamente: "+ odontologoARetornar);



        }catch (Exception e){
            try {
                conexion.rollback();
                logger.error("Hubo un problema, se hizo un rollback a la transaccion");
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }finally {
                try {
                    conexion.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                }
            }
            logger.error(e.getMessage());
        }finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection conexion = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo = null;


    try {
        conexion = H2Connection.getConnection();
        Statement sentencia = conexion.createStatement();
        ResultSet tabla_datos = sentencia.executeQuery(SELECT_ALL);
        while(tabla_datos.next()){
            Integer id = tabla_datos.getInt(1);
            Integer matricula = tabla_datos.getInt(2);
            String nombre = tabla_datos.getString(3);
            String apellido = tabla_datos.getString(4);
            odontologo = new Odontologo(id, matricula, nombre, apellido);

            logger.info("odontologo encontrado: "+ odontologo);
            listaOdontologos.add(odontologo);
        }

    } catch (Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
    } finally {
        try {
            conexion.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

        return listaOdontologos;
    //la funcion termina aqui
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo OdontologoEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet tabla_datos = preparedStatement.executeQuery();
            if(tabla_datos.next()){
                Integer idDB = tabla_datos.getInt(1);
                Integer matriculaDB = tabla_datos.getInt(2);
                String nombreDB = tabla_datos.getString(3);
                String apellidoDB = tabla_datos.getString(4);
                OdontologoEncontrado = new Odontologo(idDB, matriculaDB, nombreDB, apellidoDB);
            }
            if(OdontologoEncontrado != null){
                logger.info("Odontologo encontrado "+ OdontologoEncontrado);
            }else logger.info("Odontologo no encontrado");

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return OdontologoEncontrado;
    }




}
