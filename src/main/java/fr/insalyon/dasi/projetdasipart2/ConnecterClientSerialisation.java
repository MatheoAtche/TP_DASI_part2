/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;

/**
 *
 * @author slabouchei
 */
public class ConnecterClientSerialisation extends Serialisation {
    
    @Override
    public void serialiser (HttpServletRequest request,HttpServletResponse response) throws IOException{
        
        JsonObject jsonContainer = new JsonObject();
        Client client = (Client)request.getAttribute("clientConnecte");
        
        //Si le client a bien été identifié, on récupère ses informations
        if(client != null) {
            jsonContainer.addProperty("idClient",String.valueOf(client.getId()));
            jsonContainer.addProperty("nomClient",client.getNom());
            jsonContainer.addProperty("prenomClient",client.getPrenom());
            jsonContainer.addProperty("courrielClient",client.getEmail());
        } else {
            //Sinon on les met toutes à null
            jsonContainer.addProperty("idClient","null");
            jsonContainer.addProperty("nomClient","null");
            jsonContainer.addProperty("prenomClient","null");
            jsonContainer.addProperty("courrielClient","null");
        }
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
    
    
}
