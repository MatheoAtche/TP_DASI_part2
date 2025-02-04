/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.projetdasipart2;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Client;


/**
 *
 * @author slabouchei
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true); 
        response.setContentType("text/html;charset=UTF-8");
        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;
        
        switch(todo){
            case "connecterClient" :
                action = new ConnecterClientAction();
                serialisation = new ConnecterClientSerialisation();
                action.executer(request);
                serialisation.serialiser(request,response);
                break;
            case "connecterEmploye" :
                action = new ConnecterEmployeAction();
                serialisation = new ConnecterEmployeSerialisation();
                action.executer(request);
                serialisation.serialiser(request, response);
                break;
                
            case "chargerPageAccueilClient" :
                if((Client)session.getAttribute("client") == null) {
                    response.sendError(403,"Forbidden(No User)");
                }
                action = new PageAccueilClientAction();
                serialisation = new PageAccueilClientSerialisation();
                action.executer(request);
                serialisation.serialiser(request,response);
                break;
                
            case "inscrireClient" :
                action = new InscrireClientAction();
                serialisation = new InscrireClientSerialisation();
                action.executer(request);
                serialisation.serialiser(request,response);
                break;
                
            case "ajouterIntervention" :
                if((Client)session.getAttribute("client") == null) {
                    response.sendError(403,"Forbidden(No User)");
                }
                action = new AjouterInterventionAction();
                serialisation = new AjouterInterventionSerialisation();
                action.executer(request);
                serialisation.serialiser(request,response);
                break;
                
            case "deconnexionClient" :
                if((Client)session.getAttribute("client") == null) {
                    response.sendError(403,"Forbidden(No User)");
                }
                action = new DeconnexionClientAction();
                action.executer(request);
                break;
                
            case "deconnexionEmploye" :
                action = new DeconnexionEmployeAction();
                action.executer(request);
                break;
                
            case "ClotureIntervention" :
                action = new ClotureInterventionAction();
                action.executer(request);
                break;
                
            case "GetInter" :
                action = new GetInterventionAction();
                serialisation = new GetInterventionSerialisation();
                action.executer(request);
                serialisation.serialiser(request,response);
                break;
                
            case "MapTableauBord" :
                action = new MapTableauBordAction();
                serialisation = new MapTableauBordSerialisation();
                action.executer(request);
                serialisation.serialiser(request, response);
                break;
                
            case "Detail_Inter":
                System.out.println("servlet1");
                action = new DetailInterAction();
                serialisation = new DetailInterSerialisation();
                action.executer(request);
                serialisation.serialiser(request, response);
                System.out.println("servlet2");
                break;
                
            default :
                PrintWriter out2 = response.getWriter();
                out2.print("Default");
        }
    }
    private static final Logger LOG = Logger.getLogger(ActionServlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
