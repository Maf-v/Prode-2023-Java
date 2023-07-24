package ar.com.prueba.controllers;

import java.io.IOException;
import java.util.List;

import ar.com.deserialize.User;
import ar.com.prueba.dao.iUserDAO;
import ar.com.prueba.dao.implement.UserDAOMysqlImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateUser")
public class CreateUserController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("inputUsuario");
		String password = req.getParameter("inputContraseña");
		
		iUserDAO dao = new UserDAOMysqlImpl();
		User user = new User(uid, password);
		
		try {
			dao.create(user);
			req.setAttribute("success", "Usuario creado");
			
			HttpSession session = req.getSession();
			session.setAttribute("uid", uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}
