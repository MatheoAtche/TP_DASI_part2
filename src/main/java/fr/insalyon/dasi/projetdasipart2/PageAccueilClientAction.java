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
        
        Client client = (Client)request.getSession().getAttribute("client");
        Service service = new Service();
        
        List<Intervention> interventionsFaites = service.getInterventions(client);
        List<Intervention> interventionsRetournees = new ArrayList<>();
        
        for(Intervention intervention : interventionsFaites) {
            interventionsRetournees.add(intervention);
        }
        
        request.setAttribute("prenomClient",client.getPrenom());
        request.setAttribute("interventionsClient",interventionsRetournees);
        
        return true;
    }
    
}
