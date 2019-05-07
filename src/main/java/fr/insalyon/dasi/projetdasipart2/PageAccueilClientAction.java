/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;

/**
 *
 * @author slabouchei
 */
public class PageAccueilClientAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request) {
        
        Client client = (Client)request.getSession().getAttribute("client");
        request.setAttribute("prenomClient",client.getPrenom());
        
        return true;
    }
    
}
