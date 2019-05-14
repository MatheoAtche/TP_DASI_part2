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
public class ClotureInterventionAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
        Employe employe = (Employe) request.getSession().getAttribute("employe");
        Intervention i = service.getInterventionEnCours(employe);
        boolean choix;
        choix = "Succes".equals(request.getParameter("choix"));

        service.CloturerIntervention(i, choix, request.getParameter("message"));
        return true;
    }
    
}
