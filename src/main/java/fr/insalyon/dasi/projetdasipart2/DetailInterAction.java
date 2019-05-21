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
public class DetailInterAction extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
        String id = request.getParameter("id");
        System.out.println("Action1");
        Intervention i = service.getInterventionParId(Long.parseLong(id));
        System.out.println("Action2");
        request.setAttribute("type", i.typeToString());
        request.setAttribute("message", i.getDescription());
        request.setAttribute("heure", i.getDebut());
        request.setAttribute("commentaire", i.getMessageFin());
        return true;
    }
}