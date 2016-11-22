package presentation;

import dataaccess.UserMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael
 */
@WebServlet(name="Login", urlPatterns={"/Login"})
public class Login extends HttpServlet {
   
 
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserMapper um = new UserMapper();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordTwo = request.getParameter("passwordTwo");
        if(passwordTwo.isEmpty()){
            boolean isAuthenticated = um.authenticateUser(username, password);
            if(isAuthenticated){
                response.getWriter().print("Du er nu logget ind");
//                while(User.size){
//                    response.getWriter().print();
//                }
            }else{
                response.getWriter().print("du er ikke logget ind");
            }
        }else{
            if(password.equals(passwordTwo) && !username.isEmpty()){
                //opret bruger i DB
                boolean isCreated = um.createUser(username, password);
                if (isCreated){
                    response.getWriter().print("Du er nu oprettet som bruger");
                }else{
                    response.getWriter().print("Kan ikke oprette bruger");
                }
            }else{
                response.getWriter().print("dine password er ikke ens");
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}