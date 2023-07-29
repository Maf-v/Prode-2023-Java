package ar.com.prueba;

import ar.com.deserialize.User;
import ar.com.prueba.dao.iUserDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.prueba.dao.implement.UserDAOMysqlImpl;


@WebServlet("/Login")
public class Login extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("inputUsuario");
		String password = req.getParameter("inputPassword");
		
		iUserDAO dao = new UserDAOMysqlImpl();
		User user = null;
		try {
			user = dao.getByUid(uid);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Usuario no encontrado");
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
		
		if(!user.getPassword().equals(password)) {
			req.setAttribute("error", "Contraseña incorrecta");
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("sucess", "Login correcto");
			
			HttpSession session = req.getSession();

			session.setAttribute("uid", uid);
			getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		}
		
	}

}
