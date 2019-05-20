/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervention;

/**
 *
 * @author mathe
 */
public class MapTableauBordSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //On récpère toutes les données des interventions 
        JsonObject jsonContainer = new JsonObject();
        JsonArray jsonArrayInterventions = new JsonArray();
        List<Intervention> interventionsJour = (List<Intervention>) request.getAttribute("interventionsJour");
        
        for(Intervention intervention : interventionsJour) {
            JsonObject jsonInterventions = new JsonObject();
            jsonInterventions.addProperty("lat",(Number)(intervention.getClient().getAdresse().getLatitude()));
            jsonInterventions.addProperty("long",(Number)(intervention.getClient().getAdresse().getLongitude()));
            jsonInterventions.addProperty("id",intervention.getId());
            String statut;
            if(intervention.getStatut() == Intervention.Statut.ECHEC) {
                statut = "ECHEC";
            } else if(Intervention.Statut.SUCCES == intervention.getStatut()) {
                statut = "SUCCES";
            } else {
                statut = "EN COURS";
            }
            jsonInterventions.addProperty("statut",statut);

            jsonArrayInterventions.add(jsonInterventions);
            
        }
        
        jsonContainer.add("interventionsJour",jsonArrayInterventions);
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
        
    }
    
}