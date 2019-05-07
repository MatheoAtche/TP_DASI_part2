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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.Intervention;
import metier.service.TimeService;

/**
 *
 * @author slabouchei
 */
public class PageAccueilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        JsonArray jsonArrayInterventions = new JsonArray();
        
        String prenomClient = (String)request.getAttribute("prenomClient");
        List<Intervention> interventionsClient = (List<Intervention>) request.getAttribute("interventionsClient");
        
        for(Intervention intervention : interventionsClient) {
            
            JsonObject jsonInterventions = new JsonObject();
            
            //On recupère la date au format STRING
            jsonInterventions.addProperty("dateDebut",TimeService.dateComplete(intervention.getDebut()));
            
            //On récupère le type
            jsonInterventions.addProperty("type",intervention.typeToString());
            
            //On récupère la description
            jsonInterventions.addProperty("description",intervention.getDescription());
            
            //On recupère le statut
            String statut = intervention.getStatut() + "";
            jsonInterventions.addProperty("statut",statut);
            
            //On récupère le message de fin si c'est un succès
            if (intervention.getStatut() != Intervention.Statut.EN_COURS) {
                jsonInterventions.addProperty("dateFin",TimeService.dateToHeure(intervention.getFin()));
                jsonInterventions.addProperty("message",intervention.getMessageFin());
            } else {
                jsonInterventions.addProperty("dateFin","null");
                jsonInterventions.addProperty("message","null");
            }
            
            jsonArrayInterventions.add(jsonInterventions);
        }
        
        jsonContainer.addProperty("prenomClient",prenomClient);
        jsonContainer.add("interventionsClient",jsonArrayInterventions);
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
        
    }
    
}
