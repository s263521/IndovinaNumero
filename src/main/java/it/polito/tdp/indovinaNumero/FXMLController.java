package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.MenuItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private int NMAX = 100;
	private int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	  @FXML
	  private ResourceBundle resources;

	  @FXML
	  private URL location;

	  @FXML
	  private Button btnNuovaPartita;

	  @FXML
	  private TextField txtTentativi;

	  @FXML
	  private ProgressBar barTentativi;

	  @FXML
	  private HBox layoutTentativo;

	  @FXML
	  private TextField txtTentativiUtenete;

	  @FXML
	  private Button btnProva;

	  @FXML
	  private Button btnAbbandona;

	  @FXML
	  private TextArea txtRisultato;
	  
	  @FXML
	    private HBox layoutNuovaPartita;
	  
	  @FXML
	    private HBox layautBar;
	  
	/*  @FXML
	  private MenuItem mnFacile;

	  @FXML
	  private MenuItem mnMedio;

	  @FXML
	  private MenuItem mnDifficile;
*/
	  @FXML
	  private MenuButton mnScelta;
	  
	   @FXML
	    void doDifficile(ActionEvent event) {
	    	this.mnScelta.setText("Modalità difficile");
	    	NMAX = 300;
	    	TMAX = 8;
	    	this.layoutNuovaPartita.setDisable(false);
	    	this.layautBar.setVisible(true);
	    	this.txtRisultato.setVisible(true);
	    }

	    @FXML
	    void doFacile(ActionEvent event) {
	    	this.mnScelta.setText("Modalità facile");
	    	NMAX = 20;
	    	TMAX = 10;
	    	this.layoutNuovaPartita.setDisable(false);
	    	this.layautBar.setVisible(true);
	    	this.txtRisultato.setVisible(true);
	    }

	    @FXML
	    void doMedio(ActionEvent event) {
	    	this.mnScelta.setText("Modalità media");
	    	NMAX = 200;
	    	TMAX = 7;
	    	this.layoutNuovaPartita.setDisable(false);
	    	this.layautBar.setVisible(true);
	    	this.txtRisultato.setVisible(true);
	    }
	    

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//Gestione inizia nuova partita
    	this.segreto = (int) ((Math.random()*NMAX) +1);
    	this.inGioco = true;
    	this.tentativiFatti = 0;
    	txtRisultato.clear();
    	txtTentativiUtenete.clear();
    	this.layoutTentativo.setVisible(true);
    	this.btnAbbandona.setVisible(true);
    	this.barTentativi.setProgress(0);
    	
    	//Gestione interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX));
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//Lettura dell'Imput dell'utente
    	String ts = txtTentativiUtenete.getText();  
    	int tentativo;
    	double avanzamento;
    	
    	try {
    		  tentativo = Integer.parseInt(ts);
    	} catch(NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero\n");
    		return;
    	}
    	
    	this.tentativiFatti++;   
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiFatti));
    	avanzamento = this.tentativiFatti / (TMAX*1.0);
    	this.barTentativi.setProgress(avanzamento);
    	
    	//Ho indovinato
    	if(tentativo == this.segreto) {
    		txtRisultato.appendText("Hai vinto con " + this.tentativiFatti + " tentativi");
    		this.inGioco = false;
    		this.layoutTentativo.setVisible(false);
    		this.btnAbbandona.setVisible(false);
    		return;
    	}
    	
    	//Se ho esaurito i tentativi
    	if (this.tentativiFatti == TMAX) {
    		txtRisultato.appendText("Hai perso. Il numero segreto era " + this.segreto +  "\n");
    		this.inGioco = false;
    		this.layoutTentativo.setVisible(false);
    		this.btnAbbandona.setVisible(false);
    		return;
    	}
    	
    	//Non ho vinto-> devo informare l'utente
    	if (tentativo < this.segreto) {
    		txtRisultato.appendText("Tentativo troppo basso. Il numero da te giocato è: " + tentativo + "\n");
    	} else {
    		txtRisultato.appendText("Tentativo troppo alto. Il numero da te giocato è: " + tentativo + "\n");
    	}

    	this.txtTentativiUtenete.clear();
    	
    }
    
    @FXML
    void doAbbandona(ActionEvent event) {
    	inGioco=false;
    	txtRisultato.clear();
    	txtTentativiUtenete.clear();
    	this.layoutTentativo.setVisible(false);
    	this.barTentativi.setVisible(false);
    	this.txtTentativi.setText(Integer.toString(TMAX));
    	
    }
     
 
    
    @FXML
    void initialize() {
     /*   assert mnScelta != null : "fx:id=\"mnScelta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert mnFacile != null : "fx:id=\"mnFacile\" was not injected: check your FXML file 'Scene.fxml'.";
        assert mnMedio != null : "fx:id=\"mnMedio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert mnDifficile != null : "fx:id=\"mnDifficile\" was not injected: check your FXML file 'Scene.fxml'.";*/
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativiUtenete != null : "fx:id=\"txtTentativiUtenete\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAbbandona != null : "fx:id=\"btnAbbandona\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}
