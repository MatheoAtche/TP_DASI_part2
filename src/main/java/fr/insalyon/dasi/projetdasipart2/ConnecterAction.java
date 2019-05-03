/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author slabouchei
 */
public class ConnecterAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(true);
        Client client = null;
        client = service.AuthentificationClient(request.getParameter("login"), request.getParameter("password"));
        if(client != null)
        {
            session.setAttribute("idPerssone",client.getId());
            request.setAttribute("nomClient",client.getNom());
        } else {
            request.setAttribute("nomClient","Aucun");
        }
        return true;
    }
    
    
    
}
