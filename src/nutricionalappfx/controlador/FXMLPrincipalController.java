/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nutricionalappfx.dominio.pojo.Colaborador;

/**
 * FXML Controller class
 *
 * @author akyer
 */
public class FXMLPrincipalController implements Initializable {
    
    private Colaborador colaborador;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbRol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarInformacion(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
    private void mostrarInfoPantalla() {
        if (colaborador != null) {
            lbNombre.setText("Bienvenido(a) " + colaborador.toString());
            lbRol.setText("Rol: " + colaborador.getRol());
        }
    }
}
 