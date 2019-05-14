/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author mathe
 */
public class GetInterventionAction extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
        Employe employe = (Employe)request.getSession().getAttribute("employe");
        Intervention i = service.getInterventionEnCours(employe);
        System.out.println(i);
        System.out.println(i.getClient());
        System.out.println(i.getClient().getNom());
        request.setAttribute("client", i.getClient().getNom() + " " + i.getClient().getPrenom());
        request.setAttribute("tel", i.getClient().getTelephone());
        request.setAttribute("type", i.typeToString());
        request.setAttribute("message", i.getDescription());
        request.setAttribute("heure", i.getDebut());
        request.setAttribute("adresse", i.getClient().getAdresse().toString());
        return true;
    }
}