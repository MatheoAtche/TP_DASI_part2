/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author slabouchei
 */
public class DeconnexionClientAction extends Action {

    @Override
    public boolean executer(HttpServletRequest request) {
        
        request.getSession().setAttribute("client",null);
        return true;
    }
    
}
