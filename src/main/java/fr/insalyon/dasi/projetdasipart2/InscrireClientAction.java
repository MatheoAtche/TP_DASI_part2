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
        //On cree la date et l'adresse
        Adresse adresse = new Adresse(request.getParameter("ad1"),request.getParameter("ad2"),request.getParameter("ville"),"");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        

        //On vérifie que les champs ne sont pas nuls
        if(request.getParameter("nom").equals("") || request.getParameter("prenom").equals("") || request.getParameter("date").equals("") || 
            request.getParameter("tel").equals("") || request.getParameter("login").equals("") || 
            request.getParameter("login").equals("") || request.getParameter("password").equals("") ||
            request.getParameter("ad1").equals("") || request.getParameter("ville").equals("")) {
            
            request.setAttribute("resultatInscription","champsVide");
            return true;
        }
        
        try {
            date = dateFormat.parse(request.getParameter("date"));
        } catch (ParseException ex) {
            Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        //On cree le client
        Client client = new Client(request.getParameter("nom"),request.getParameter("prenom"),
                                    date,adresse,Long.parseLong(request.getParameter("tel")),
                                    request.getParameter("login"),request.getParameter("password"));
        InscriptionResult ajout = service.InscriptionClient(client);

        request.setAttribute("resultatInscription","KO");
        //Si la création a reussi, on s'authentifie
        if(ajout==InscriptionResult.SUCCES) {
            client = service.AuthentificationClient(request.getParameter("login"),request.getParameter("password"));
            if(client != null) { 
                //et si on a reussi à s'authentifier, on met à jour la variable de session
                request.getSession().setAttribute("client", client);
                request.setAttribute("resultatInscription","OK");
            } else {
                //Sinon on indique que l'authentification a échoué
                request.getSession().setAttribute("client", null);
                request.setAttribute("resultatInscription","KO");
            }
            
        } 

        return true;
    }

}
