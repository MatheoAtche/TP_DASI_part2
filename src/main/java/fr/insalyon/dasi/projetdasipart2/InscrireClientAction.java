/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Adresse;
import metier.modele.Client;
import metier.service.InscriptionResult;
import metier.service.Service;

/**
 *
 * @author Stéph
 */
public class InscrireClientAction extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request) {
        Service service = new Service();
        Adresse adresse = new Adresse(request.getParameter("ad1"),request.getParameter("ad2"),request.getParameter("ville"),"");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(request.getParameter("date"));
        } catch (ParseException ex) {
            Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        Client client = new Client(request.getParameter("nom"),request.getParameter("prenom"),
                                    date,adresse,Long.parseLong(request.getParameter("tel")),
                                    request.getParameter("login"),request.getParameter("password"));
        InscriptionResult ajout = service.InscriptionClient(client);

        if(ajout==InscriptionResult.SUCCES) {
            client = service.AuthentificationClient(request.getParameter("login"),request.getParameter("password"));
            if(client != null) {
                request.getSession().setAttribute("client", client);
                request.setAttribute("resultatInscription","OK");
            } else {
                request.getSession().setAttribute("client", null);
                request.setAttribute("resultatInscription","KO");
            }
            
        } else {
            client = null;
            request.getSession().setAttribute("client",client);
            request.setAttribute("prenomClient",client.getPrenom());
            request.setAttribute("resultatInscription","KO");
        }

        return true;
    }

}
