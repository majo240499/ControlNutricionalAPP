/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutricionalappfx.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nutricionalappfx.dominio.contrato.IPacienteRepository;
import nutricionalappfx.dominio.pojo.OperacionResultado;
import nutricionalappfx.dominio.pojo.Paciente;
import nutricionalappfx.modelo.ConexionBD;

/**
 *
 * @author Maria Jose
 */
public class PacienteDAO implements IPacienteRepository {

    @Override
    public List<Paciente> obtenerPacienteNutriologo(int idNutriologo) throws RuntimeException {
       List<Paciente> pacientes = new ArrayList<>();
       
       String sentencia = "SELECT idPaciente, codigo, nombre, apellidoPaterno, apellidoMaterno, "
               + "fechaNacimiento, altura, pesoInicial, sexo,telefono, correo, "
               + "idNutriologo FROM pacientes WHERE idNutriologo = ?";
       try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement ps = conexion.prepareStatement(sentencia)) {
           
           ps.setInt(1, idNutriologo);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setCodigo(rs.getString("codigo"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellidoPaterno(rs.getString("apellidoPaterno"));
                paciente.setApellidoMaterno(rs.getString("apellidoMaterno"));
                paciente.setFechaNacimiento(rs.getString("fechaNacimiento"));
                paciente.setAltura(rs.getInt("altura"));
                paciente.setPesoInicial(rs.getFloat("pesoInicial"));

                String sexoString = rs.getString("sexo");
                if (sexoString != null && !sexoString.isEmpty()) {
                    paciente.setSexo(sexoString.charAt(0));
                }
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setCorreo(rs.getString("correo"));
                paciente.setIdNutriologo(rs.getInt("idNutriologo"));

                pacientes.add(paciente);
            }
        }
    } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los pacientes del nutriólogo", e);
    }

    return pacientes;

    }
    
    @Override
    public OperacionResultado registrarPaciente(Paciente nuevoPaciente) throws RuntimeException {
        OperacionResultado resultado = new OperacionResultado();
        String sentencia = "INSERT INTO pacientes (codigo, nombre, apellidoPaterno, "
                + "apellidoMaterno, fechaNacimiento, altura, pesoInicial, sexo, telefono, correo, idNutriologo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sentencia);) {
            ps.setString(1, nuevoPaciente.getCodigo());
            ps.setString(2, nuevoPaciente.getNombre());
            ps.setString(3, nuevoPaciente.getApellidoPaterno());
            ps.setString(4, nuevoPaciente.getApellidoMaterno());
            ps.setString(5, nuevoPaciente.getFechaNacimiento());
            ps.setInt(6, nuevoPaciente.getAltura());
            ps.setFloat(7, nuevoPaciente.getPesoInicial());
            ps.setString(8, "" + nuevoPaciente.getSexo());
            ps.setString(9, nuevoPaciente.getTelefono());
            ps.setString(10, nuevoPaciente.getCorreo());
            ps.setInt(11, nuevoPaciente.getIdNutriologo());
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                resultado.setError(false);
                resultado.setMensaje("Informacion del paciente, registrada correctamente.");
            } else {
                resultado.setError(true);
                resultado.setMensaje("Lo sentimos, el registro del paciente no pudo ser guardada, "
                        + "por favor revise la información y vuelva a intentarlo.");
            }
            
            return resultado;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error de conexión con el almacenamiento.");
        }
    }

    @Override
    public OperacionResultado editarPaciente(Paciente paciente) throws RuntimeException {
        return null;
    }

    @Override
    public OperacionResultado eliminarPaciente(int idPaciente) throws RuntimeException {
        return null;
    }

    @Override
    public boolean verificarExistenciaCodigoPaciente(String codigo) throws RuntimeException {
        return false;
    }


    
}
