/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervention;

/**
 *
 * @author mathe
 */
public class GetInterventionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.addProperty("client", (String) request.getAttribute("client"));
        jsonContainer.addProperty("tel", String.valueOf(request.getAttribute("tel")));
        jsonContainer.addProperty("type", (String) request.getAttribute("type"));
        jsonContainer.addProperty("message", (String) request.getAttribute("message"));
        jsonContainer.addProperty("heure", String.valueOf(request.getAttribute("heure")));
        jsonContainer.addProperty("adresse", (String) request.getAttribute("adresse"));
        jsonContainer.addProperty("lat",(Number) request.getAttribute("lat"));
        jsonContainer.addProperty("long", (Number) request.getAttribute("long"));
        System.out.println("Le client est : " + (String) request.getAttribute("client"));
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
    
}
