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
public class InscrireClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        String reponseInscription = (String)request.getAttribute("resultatInscription");
        //En fonction des résultats de l'inscription, on renvoit un résultat différent
        if(reponseInscription.equals("OK")) {
            jsonContainer.addProperty("resultatInscription","OK");
        } else if(reponseInscription.equals("champsVide")) {
            jsonContainer.addProperty("resultatInscription","champsVide");
        }else {
           jsonContainer.addProperty("resultatInscription","KO");
        }
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
        
    }


}
