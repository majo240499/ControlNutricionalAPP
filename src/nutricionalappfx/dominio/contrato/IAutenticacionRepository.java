/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutricionalappfx.dominio.contrato;

import nutricionalappfx.dominio.pojo.Colaborador;
import nutricionalappfx.dominio.pojo.ColaboradorEstatus;

/**
 *
 * @author Maria Jose
 */
public interface IAutenticacionRepository {
    
    public int verificarCredenciales(String noPersonal, String password) throws RuntimeException;
    
    public ColaboradorEstatus verificarEstatusColaborador(int idColaborador) throws RuntimeException;
    
    public Colaborador obtenerInformacionColaborador(int idColaborador) throws RuntimeException;
}
