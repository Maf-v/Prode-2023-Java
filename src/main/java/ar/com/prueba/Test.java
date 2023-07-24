package ar.com.prueba;

import javax.servlet.annotation.WebServlet;

@WebServlet("/test")

public class Test {
	public Test() {
		System.out.println("Deberia aparecer");
	}
}
