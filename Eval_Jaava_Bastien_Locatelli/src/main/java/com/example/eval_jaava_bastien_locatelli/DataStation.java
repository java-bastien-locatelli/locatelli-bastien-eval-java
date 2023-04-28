package com.example.eval_jaava_bastien_locatelli;

public class DataStation {
    private String nom;
    private String prenom;
    private String addresse;
    private String nomStas;
    private String codePost;
    private String commune;
    private String nbBornette;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getNomStas() {
        return nomStas;
    }

    public void setNomStas(String nomStas) {
        this.nomStas = nomStas;
    }

    public String getCodePost() {
        return codePost;
    }

    public void setCodePost(String codePost) {
        this.codePost = codePost;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getNbBornette() {
        return nbBornette;
    }

    public void setNbBornette(String nbBornette) {
        this.nbBornette = nbBornette;
    }

    public static String toJson(DataStation dt) {
        String finale = String.format( """
        
                {
                      "nom": %s ,\s
                      "prenom": %s,\s
                      "address": %s,\s
                      "Nom_Station": %s,\s
                      "Code_Postale": %s,\s
                      "Commune": %s,\s
                      "NbBornette": %s,\s
                  
                    },
                """, dt.getNom(),dt.getPrenom(),dt.getAddresse(),dt.getNomStas(),dt.getCodePost(),dt.getCommune(),dt.getNbBornette());
        return finale;
    }
}
