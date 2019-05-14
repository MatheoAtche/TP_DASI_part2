/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Intervention;
import metier.modele.InterventionAnimal;
import metier.modele.InterventionIncident;
import metier.modele.InterventionLivraison;
import metier.service.Service;

/**
 *
 * @author Stéph
 */
public class AjouterInterventionAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request) {
  
        Service service = new Service();
        Intervention intervention = null;
        String typeIntervention = (String)request.getParameter("type");
        
        //En fonction du type de l'intervention,
        //on crée la bonne
        switch(typeIntervention) {
            case "livraison" :
                InterventionLivraison interventionLivraison = null;
                interventionLivraison = new InterventionLivraison();
                interventionLivraison.setObjet(request.getParameter("objet"));
                interventionLivraison.setDescription(request.getParameter("description"));
                interventionLivraison.setEntreprise(request.getParameter("entreprise"));
                intervention = interventionLivraison;
                break;
            case "animal" :
                InterventionAnimal interventionAnimal = null;
                interventionAnimal = new InterventionAnimal();
                interventionAnimal.setAnimal(request.getParameter("animal"));
                interventionAnimal.setDescription(request.getParameter("description"));
                intervention = interventionAnimal;
                break;
            default:
                InterventionIncident interventionIncident = null;
                interventionIncident = new InterventionIncident();
                interventionIncident.setDescription(request.getParameter("description"));
                intervention = interventionIncident;
        }

        Client client = (Client)request.getSession().getAttribute("client");
        intervention.setClient(client);
        intervention.setDebut(new Date());
        intervention.setStatut(Intervention.Statut.EN_COURS);

        //On ajoute l'intervention
        if (service.EnregistrerDemande(intervention)) {
            request.setAttribute("enregistrementIntervention","OK");
        } else {
            request.setAttribute("enregistrementIntervention","KO");
        }
        
        return true;
    }
    
}
