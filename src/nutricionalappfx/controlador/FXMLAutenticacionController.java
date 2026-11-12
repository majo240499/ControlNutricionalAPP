/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nutricionalappfx.NutricionalAppFX;
import nutricionalappfx.dominio.AutenticacionDM;
import nutricionalappfx.dominio.pojo.Autenticacion;
import nutricionalappfx.dominio.pojo.Colaborador;
import nutricionalappfx.modelo.dao.AutenticacionDAO;
import nutricionalappfx.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author akyer
 */
public class FXMLAutenticacionController implements Initializable {

    @FXML
    private TextField tfNoPersonal;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lbErrorNoPersonal;
    @FXML
    private Label lbErrorPassword;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void handleButtonIngresar(ActionEvent event) {
        String noPersonal = tfNoPersonal.getText();
        String password = pfPassword.getText();
        if (validarCampos()) {
            validarCredenciales(noPersonal, password);
        }
    }
    
    private void validarCredenciales(String noPersonal, String password) {
        AutenticacionDM autenticacionDM = new AutenticacionDM(new AutenticacionDAO());
        try {
            Autenticacion respuesta = autenticacionDM.verificarCredencialesColaborador(noPersonal, password);
            if (respuesta.isCredencialesCorrectas() && respuesta.isActivo()) {
                Utilidades.mostrarAlertaSimple("Credenciales correctas", respuesta.getMensaje(), 
                        Alert.AlertType.INFORMATION);
                irPantallaPrincipal(respuesta.getColaborador());
            } else {
                Utilidades.mostrarAlertaSimple("Lo sentimos :(",respuesta.getMensaje() , 
                        Alert.AlertType.WARNING);
            }
        } catch (RuntimeException ex) {
            Utilidades.mostrarAlertaSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean validarCampos() {
        boolean esValido = true;
        lbErrorNoPersonal.setText("");
        lbErrorPassword.setText("");
        if (tfNoPersonal.getText().isEmpty()) {
            lbErrorNoPersonal.setText("El numero de personal es obligatorio.");
            esValido = false;
        }
        if (pfPassword.getText().isEmpty()) {
            lbErrorPassword.setText("Debes ingresar la contraseña.");
        }
        return esValido;
    }
    
    private void irPantallaPrincipal(Colaborador colaborador) {
        try {
        Stage escenario = (Stage) tfNoPersonal.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(NutricionalAppFX.class.getResource("vista/FXMLPrincipal.fxml"));
        Parent vista = loader.load();
        FXMLPrincipalController controlador = loader.getController();
        controlador.inicializarInformacion(colaborador);
        Scene escena = new Scene(vista);
        escenario.setScene(escena);
        escenario.setTitle("Control Nutricional");
        escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
