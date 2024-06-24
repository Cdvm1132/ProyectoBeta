package com.example.sepienteescalerasbeta;

import com.example.sepienteescalerasbeta.Dado.Dado;
import com.example.sepienteescalerasbeta.GuardadoDatos.GuardadoDatos;
import com.example.sepienteescalerasbeta.Jugador.Jugador;
import com.example.sepienteescalerasbeta.Partida.Partida;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class HelloController {

    @FXML
    private ImageView DadoCara6;
    @FXML
    private ImageView DadoCara5;
    @FXML
    private ImageView DadoCara4;
    @FXML
    private ImageView DadoCara3;
    @FXML
    private ImageView DadoCara2;
    @FXML
    private ImageView DadoCara1;
    @FXML
    private Label LBLSerpiente;

    @FXML
    private Pane PartidaTerminado;
    @FXML
    private Pane Pausa;
    @FXML
    private Pane Tablero;
    @FXML
    public GridPane[][] GPaneTablero;
    @FXML
    private Pane SeleccionTablero;
    @FXML
    private Rectangle PrimerTablero;
    private Clip clipSound1;
    private Clip clipSound2;
    private Clip clipSound3;
    private static GuardadoDatos guardadoDatos = GuardadoDatos.obtenerInstancia();
    private static List<Jugador> jugadores = new ArrayList<>();
    private static Partida partida;
    private static com.example.sepienteescalerasbeta.Tablero.Tablero tablero;
    private String TextoAnimado = "Bienvenido Jugador";
    private String TextoDado1 ="Avanzas 1 espacio";
    private String TextoDado2 ="Avanzas 2 espacios";
    private String TextoDado3 ="Avanzas 3 espacios";
    private String TextoDado4 ="Avanzas 4 espacios";
    private String TextoDado5 ="Avanzas 5 espacios";
    private String TextoDado6 ="Avanzas 6 espacios";
    private int charIndex = 0;
    private Timeline timeline;
    Dado dado = new Dado();
    int resultadoDado;

    @FXML
    private void initialize() {
        try {
            clipSound1 = AudioSystem.getClip();
            clipSound2 = AudioSystem.getClip();
            clipSound3 = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Sonido1() {
        playSound(clipSound1, "C:\\Users\\Carlos David\\IdeaProjects\\SepienteEscalerasbeta\\src\\main\\resources\\com\\WhatsApp-Audio-2024-06-21-at-8.10.58-PM.wav", 2000);
    }

    @FXML
    private void Sonido2() {
        stopSound(clipSound1);
        stopSound(clipSound3);
        playSound(clipSound2, "C:\\Users\\Carlos David\\IdeaProjects\\SepienteEscalerasbeta\\src\\main\\resources\\com\\WhatsApp-Audio-2024-06-22-at-8.44.07-AM-_1_.wav", 55000);
    }

    @FXML
    private void Sonido3() {
        stopSound(clipSound2);
        playSound(clipSound3, "C:\\Users\\Carlos David\\IdeaProjects\\SepienteEscalerasbeta\\src\\main\\resources\\com\\WhatsApp-Audio-2024-06-22-at-8.44.07-AM.wav", 55000);
    }
    private void playSound(Clip clip, String soundFilePath, int duration) {
        new Thread(() -> {
            try {
                File soundFile = new File(soundFilePath);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                clip.open(audioInputStream);
                clip.start();
                Thread.sleep(duration);
                clip.stop();
                clip.close();
                audioInputStream.close();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void stopSound(Clip clip) {
        if (clip.isRunning()) {
            clip.stop();
            clip.flush();
        }
    }
    @FXML
    protected void SelecionandoTablero1(){

        PrimerTablero.setVisible(true);
        Tablero.setVisible(true);
        SeleccionTablero.setVisible(false);
        handleStartAnimation();
        Sonido3();


    }
    @FXML
    protected void SelecionandoTablero2(){
        Tablero.setVisible(true);
        PrimerTablero.setVisible(false);
        SeleccionTablero.setVisible(false);

    }
    @FXML
    protected void  PausarJuego(){
        Pausa.setVisible(true);
        Tablero.setVisible(false);;
        Sonido2();

    }

    @FXML
    protected  void ReanudarJuego(){
        Pausa.setVisible(false);
        Tablero.setVisible(true);
        Sonido3();
    }
    @FXML
    private void handleStartAnimation() {
        Sonido1();
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.stop();
        }

        LBLSerpiente.setText("");
        charIndex = 0;

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (charIndex < TextoAnimado.length()) {
                LBLSerpiente.setText(LBLSerpiente.getText() + TextoAnimado.charAt(charIndex));
                charIndex++;

            } else {
                timeline.stop();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }


 @FXML
 public void TirarDado(){
      resultadoDado = dado.tirar();
    switch (resultadoDado){
        case 1:
            DadoCara1.setVisible(true);
            DadoCara2.setVisible(false);
            DadoCara3.setVisible(false);
            DadoCara4.setVisible(false);
            DadoCara5.setVisible(false);
            DadoCara6.setVisible(false);
            break;
        case 2:
            DadoCara1.setVisible(false);
            DadoCara2.setVisible(true);
            DadoCara3.setVisible(false);
            DadoCara4.setVisible(false);
            DadoCara5.setVisible(false);
            DadoCara6.setVisible(false);
            break;
        case 3:
            DadoCara1.setVisible(false);
            DadoCara2.setVisible(false);
            DadoCara3.setVisible(true);
            DadoCara4.setVisible(false);
            DadoCara5.setVisible(false);
            DadoCara6.setVisible(false);
            break;
        case 4:
            DadoCara1.setVisible(true);
            DadoCara2.setVisible(false);
            DadoCara3.setVisible(false);
            DadoCara4.setVisible(true);
            DadoCara5.setVisible(false);
            DadoCara6.setVisible(false);
            break;
        case 5:
            DadoCara1.setVisible(false);
            DadoCara2.setVisible(false);
            DadoCara3.setVisible(false);
            DadoCara4.setVisible(false);
            DadoCara5.setVisible(true);
            DadoCara6.setVisible(false);
            break;
        case 6:
            DadoCara1.setVisible(false);
            DadoCara2.setVisible(false);
            DadoCara3.setVisible(false);
            DadoCara4.setVisible(false);
            DadoCara5.setVisible(false);
            DadoCara6.setVisible(true);
            break;
    }Sonido1();
     if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
         timeline.stop();
     }

     LBLSerpiente.setText("");
     charIndex = 0;

     timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
         if (charIndex < TextoDado1.length() && resultadoDado==1) {
             LBLSerpiente.setText(LBLSerpiente.getText() + TextoDado1.charAt(charIndex));
             charIndex++;

         }else if (charIndex < TextoDado2.length() && resultadoDado==2) {
             LBLSerpiente.setText(LBLSerpiente.getText() + TextoDado2.charAt(charIndex));
             charIndex++;

         }else if (charIndex < TextoDado3.length() && resultadoDado==3) {
             LBLSerpiente.setText(LBLSerpiente.getText() + TextoDado3.charAt(charIndex));
             charIndex++;

         }else if (charIndex < TextoDado4.length() && resultadoDado==4) {
             LBLSerpiente.setText(LBLSerpiente.getText() + TextoDado4.charAt(charIndex));
             charIndex++;

         }else if (charIndex < TextoDado5.length() && resultadoDado==5) {
             LBLSerpiente.setText(LBLSerpiente.getText() + TextoDado5.charAt(charIndex));
             charIndex++;

         }else if (charIndex < TextoDado6.length() && resultadoDado==6) {
             LBLSerpiente.setText(LBLSerpiente.getText() + TextoDado6.charAt(charIndex));
             charIndex++;

         }else {
             timeline.stop();
         }
     }));

     timeline.setCycleCount(Timeline.INDEFINITE);
     timeline.play();

 }

}