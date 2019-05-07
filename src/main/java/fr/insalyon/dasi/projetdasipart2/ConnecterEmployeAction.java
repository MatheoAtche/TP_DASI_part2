/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

/**
 *
 * @author matheo
 */

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

public class ConnecterEmployeAction extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
       
        Employe employe = null;
        employe = service.AuthentificationEmploye(request.getParameter("login"), request.getParameter("password"));
        if(employe != null)
        {
            request.setAttribute("employeConnecte",employe);
            request.getSession().setAttribute("employe", employe);
        } else {
            request.setAttribute("employeConnecte",null);
            request.getSession().setAttribute("employe", null);
        }
        return true;
    }
}
