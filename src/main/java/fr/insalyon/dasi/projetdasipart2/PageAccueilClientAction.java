/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author slabouchei
 */
public class PageAccueilClientAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request) {
        
        //On récupère toutes les interventions du client
        Client client = (Client)request.getSession().getAttribute("client");
        Integer nbInterventions = Integer.parseInt(request.getParameter("nb"));
        Service service = new Service();
        
        List<Intervention> interventionsFaites = service.getInterventions(client,nbInterventions);
        
        request.setAttribute("prenomClient",client.getPrenom());
        request.setAttribute("interventionsClient",interventionsFaites);
        
        return true;
    }
    
}
