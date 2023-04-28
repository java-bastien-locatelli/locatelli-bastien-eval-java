package com.example.eval_jaava_bastien_locatelli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



public class HelloApplication extends Application {

    public String text;
    Stage window;
    Button buttonData;
    Button buttonDownload;
    Button buttonPropo;
    Button buttonDel;
    Button send;
    Button sendDel;

    Scene sceneMain;
    Scene scenePropo;
    Scene sceneDel;
    GridPane layoutDel;
    GridPane layoutMain;
    GridPane layoutPropo;




    //Get Response




    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("proposition-velov.json");
        DataStation dt = new DataStation();
        URL url = new URL("https://download.data.grandlyon.com/ws/grandlyon/pvo_patrimoine_voirie.pvostationvelov/all.json?maxfeatures=-1");
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        window = stage;

        //INIT DES BUTTONS
        buttonData = new Button("Consulter les données");
        buttonDownload = new Button("Télécharger le fichier");
        buttonDownload.setOnAction(e -> {
            try (FileOutputStream fos = new FileOutputStream("data-velov.json")){
                fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPropo = new Button("Proposer une station");
        buttonPropo.setOnAction(e -> window.setScene(scenePropo));
        buttonDel = new Button("Supprimer une proposition ");
        buttonDel.setOnAction(e -> window.setScene(sceneDel));
        send = new Button("Envoyer");

        sendDel = new Button("Envoyer");
        sendDel.setOnAction(e -> window.setScene(sceneMain));

        //INIT DU PREMIER LAYOUT
        layoutMain = new GridPane();
        layoutMain.setPadding(new Insets(20,20,20,20));
        layoutMain.setHgap(5);
        layoutMain.setVgap(0);
        GridPane.setConstraints(buttonData,0,0);
        GridPane.setConstraints(buttonDownload,1,0);
        GridPane.setConstraints(buttonPropo,1,1);
        GridPane.setConstraints(buttonDel,0,1);
        layoutMain.getChildren().addAll(buttonData,buttonDownload,buttonPropo,buttonDel);

        // INIT LAYOUT POUR LES PROPOSITIONS
        layoutPropo = new GridPane();
        layoutPropo.setPadding(new Insets(20,20,20,20));
        layoutPropo.setHgap(5);
        layoutPropo.setVgap(10);
        TextField nom = new TextField();
        nom.setPromptText("NOM");
        CharSequence text = nom.getCharacters();

        GridPane.setConstraints(nom,0,0);
        TextField prenom = new TextField();
        prenom.setPromptText("PRENOM");
        CharSequence textprenom = prenom.getCharacters();
        GridPane.setConstraints(prenom,0,1);
        TextField adresse = new TextField();
        adresse.setPromptText("ADRESSE");
        CharSequence textad = adresse.getCharacters();
        GridPane.setConstraints(adresse,0,2);
        TextField nomStas = new TextField();
        nomStas.setPromptText("NOM STATION");
        CharSequence textstas = nomStas.getCharacters();
        GridPane.setConstraints(nomStas,0,3);
        TextField codePost = new TextField();
        codePost.setPromptText("CODE POSTALE");
        CharSequence textpost = codePost.getCharacters();
        GridPane.setConstraints(codePost,0,4);
        TextField commune = new TextField();
        commune.setPromptText("COMMUNE");
        CharSequence textcom = commune.getCharacters();
        GridPane.setConstraints(commune,0,5);
        TextField nbBornette = new TextField();
        nbBornette.setPromptText("NOMBRE BORNETTE");
        CharSequence textBorn = nbBornette.getCharacters();
        GridPane.setConstraints(nbBornette,0,6);
        GridPane.setConstraints(send,0,7);
        layoutPropo.getChildren().addAll(nom,prenom,adresse,nomStas,codePost,commune,nbBornette,send);

        // INIT LAYOUT POUR DELETE
        layoutDel = new GridPane();
        layoutDel.setPadding(new Insets(20,20,20,20));
        layoutDel.setHgap(5);
        layoutDel.setVgap(10);
        TextField nomDel = new TextField();
        nomDel.setPromptText("NOM");
        GridPane.setConstraints(nomDel,0,0);
        TextField prenomDel = new TextField();
        prenomDel.setPromptText("PRENOM");
        GridPane.setConstraints(prenomDel,0,1);
        GridPane.setConstraints(sendDel,0,2);
        layoutDel.getChildren().addAll(nomDel,prenomDel,sendDel);


        // INIT SCENE PRINCIPALE
        sceneMain = new Scene(layoutMain,500,500);

        // INIT SCENE PROPOSITION
        scenePropo = new Scene(layoutPropo,500,500);

        // INIT SCENE POUR DELETE
        sceneDel = new Scene(layoutDel,500,500);

        window.setScene(sceneMain);
        window.setTitle("Velo-v");
        send.setOnAction(e -> { dt.setNom(text.toString());dt.setPrenom(textprenom.toString());dt.setAddresse(textad.toString()); dt.setNomStas(textstas.toString());dt.setCodePost(textpost.toString()); dt.setCommune(textcom.toString()); dt.setNbBornette(textBorn.toString());
            // sans les try/catch il y a des erreurs ils sont propose automatiquement par l ide
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }


                FileWriter fw = null;
                try {
                    fw = new FileWriter(file.getAbsoluteFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                BufferedWriter bw = new BufferedWriter(fw);
            try {
                bw.write(dt.toJson(dt).toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                    bw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}