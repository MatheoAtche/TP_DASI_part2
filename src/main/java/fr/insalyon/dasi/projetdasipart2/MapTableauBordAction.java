/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Intervention;
import metier.service.Service;

/**
 *
 * @author mathe
 */
public class MapTableauBordAction extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
        List<Intervention> interventionsJour=service.getInterventionsJour();
        request.setAttribute("interventionsJour", interventionsJour);
        return true;
    }
}