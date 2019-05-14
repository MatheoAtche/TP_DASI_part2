/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stéph
 */
public class AjouterInterventionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        String resultatAjoutIntervention = (String)request.getAttribute("enregistrementIntervention");
        
        //En fonction du résultat de l'ajout de la personne,
        //On renvoit un résultat différent
        if(resultatAjoutIntervention.equals("OK")) {
            jsonContainer.addProperty("resultatAjoutIntervention","OK");
        } else {
            jsonContainer.addProperty("resultatAjoutIntervention","KO");
        }
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
        
    }

    
}
