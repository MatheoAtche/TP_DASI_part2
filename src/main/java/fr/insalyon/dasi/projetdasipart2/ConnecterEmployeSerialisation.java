/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

/**
 *
 * @author matheo
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;

public class ConnecterEmployeSerialisation extends Serialisation {
    @Override
    public void serialiser (HttpServletRequest request,HttpServletResponse response) throws IOException{
        
        JsonObject jsonContainer = new JsonObject();
        Employe employe = (Employe)request.getAttribute("employeConnecte");
        if(employe != null) {
            jsonContainer.addProperty("idEmploye",String.valueOf(employe.getId()));
            jsonContainer.addProperty("nomEmploye",employe.getNom());
            jsonContainer.addProperty("prenomEmploye",employe.getPrenom());
            jsonContainer.addProperty("courrielEmployet",employe.getEmail());
        } else {
            jsonContainer.addProperty("idEmploye","null");
            jsonContainer.addProperty("nomEmploye","null");
            jsonContainer.addProperty("prenomEmploye","null");
            jsonContainer.addProperty("courrielEmploye","null");
        }
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
