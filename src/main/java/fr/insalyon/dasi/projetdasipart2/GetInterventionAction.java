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
        if (!employe.isDisponible()){
            Intervention i = service.getInterventionEnCours(employe);
            request.setAttribute("client", i.getClient().getNom() + " " + i.getClient().getPrenom());
            request.setAttribute("tel", i.getClient().getTelephone());
            request.setAttribute("type", i.typeToString());
            request.setAttribute("message", i.getDescription());
            request.setAttribute("heure", i.getDebut());
            request.setAttribute("adresse", i.getClient().getAdresse().toString());
            request.setAttribute("lat",i.getClient().getAdresse().getLatitude());
            request.setAttribute("long", i.getClient().getAdresse().getLongitude());
        }else{
            request.setAttribute("client", null);
            request.setAttribute("tel", null);
            request.setAttribute("type", null);
            request.setAttribute("message", null);
            request.setAttribute("heure", null);
            request.setAttribute("adresse", null);
            request.setAttribute("lat",null);
            request.setAttribute("long", null);
        }
        return true;
    }
}