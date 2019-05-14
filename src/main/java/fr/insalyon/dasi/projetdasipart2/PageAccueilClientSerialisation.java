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
import metier.modele.InterventionAnimal;
import metier.modele.InterventionLivraison;
import metier.service.TimeService;

/**
 *
 * @author slabouchei
 */
public class PageAccueilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //On récpère toutes les données des interventions 
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
            
            //On récupère l id
            jsonInterventions.addProperty("id",intervention.getId());
            
            //On recupère le statut
            String statut;
            if(intervention.getStatut() == Intervention.Statut.ECHEC) {
                statut = "ECHEC";
            } else if(intervention.getStatut() == Intervention.Statut.SUCCES) {
                statut = "SUCCES";
            } else {
                statut = "EN COURS";
            }
            jsonInterventions.addProperty("statut",statut);
            
            //On récupère le message de fin si cest termine
            if (intervention.getStatut() == Intervention.Statut.SUCCES || intervention.getStatut() == Intervention.Statut.ECHEC) {
                jsonInterventions.addProperty("supplementDate",TimeService.dateToHeure(intervention.getFin()));
                jsonInterventions.addProperty("supplementMessage",intervention.getMessageFin());
            } else {
                jsonInterventions.addProperty("supplementDate","<<... En cours ... >>");
                jsonInterventions.addProperty("supplementMessage","");
            }
            
            //En fonction du type, on ne met pas les mêmes descriptions
            if(intervention.typeToString().equals("Animal")) {
                String animal=((InterventionAnimal)intervention).getAnimal();
                jsonInterventions.addProperty("description","["+ animal +"] "+intervention.getDescription());
            } else if(intervention.typeToString().equals("Livraison")) {
                String entreprise =((InterventionLivraison)intervention).getEntreprise();
                String objet =((InterventionLivraison)intervention).getObjet();
                jsonInterventions.addProperty("description","["+ objet +"] ["+ entreprise +"] "+intervention.getDescription());
            }else {
                jsonInterventions.addProperty("description",intervention.getDescription());
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
