import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dao.UserDao;
import model.User;

public class FormServlet extends HttpServlet {
	
	private UserDao userDao = UserDao.getInstance();
	public List<String> erreur = new ArrayList<String>();
	Boolean result = false;
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp").forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	erreur.clear();
	
	String email = req.getParameter("email");
    String motDePasse = req.getParameter("mdp");
    String confirmation = req.getParameter("confirmationmdp");
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date d= null;
	try {
		if(isValid(req.getParameter("naissance"))){			
			d = formatter.parse(req.getParameter("naissance"));
		} else {
			throw new Exception("La date n'est pas valide");
		}
	} catch (Exception e1) {
		e1.printStackTrace();
		erreur.add("La date n'est pas valide");
	}
	
    User user = new User(req.getParameter("nom"), req.getParameter("prenom"), d, email, motDePasse);
    
	     
    try {
    	if (!motDePasse.isEmpty() && !motDePasse.equals(confirmation)){
    		throw new Exception("Les mots de passe sont différents");
    	} else {
    		if (motDePasse.length() < 5){
    			throw new Exception("Le mot de passe doit comporter au moins 5 caractères");
    		}
    	}
    } catch (Exception e) {
    	erreur.add(e.getMessage());
	}
    
    try {
    	if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)") ) {
			throw new Exception("L'adresse mail n'est pas valide");
    	}
    	else{
    		if(!userDao.checkEmail(user)){
    			throw new Exception("L'adresse mail existe déjà");
    		}
    	}
    } catch (Exception e) {
	    erreur.add(e.getMessage());
	}
    
	result = erreur.isEmpty();
	
	if(result){
		// Possibilité : stocker dans une session
		HttpSession s = req.getSession();
		s.setAttribute("user", user);
	
		userDao.insertUser(user);
	}
	
    req.setAttribute( "erreurs", erreur );
    req.setAttribute( "result", result );
    this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp").forward(req, resp);
    					
	}


	// Fonctions validité de la date :

	public static boolean isBissextile(int a) {
		return (a % 4 == 0 && a % 100 != 0) || a % 400 == 0;
	}
	
	public static boolean isValid(String date) {
	
		String[] dma = date.split("/");
		if (dma.length != 3 || dma[0].length() != 2 || dma[1].length() != 2 || dma[2].length() != 4)
			return false;
		int y, m, d;
		try {
			y = Integer.parseInt(dma[2]);
			m = Integer.parseInt(dma[1]);
			d = Integer.parseInt(dma[0]);
		} catch (Exception e) {
			return false;
		}
	
		return isValid(d, m, y);
	}
	
	private static boolean isValid(int d, int m, int y) {
		if (y < 1970 || y > 9999 || d < 1 || d > 31 || m < 1 || m > 12)
			return false;
	
		if (m == 2) {
			return d <= (isBissextile(y) ? 29 : 28);
		}
	
		if (m > 7)
			--m;
	
		return d <= (m % 2 == 0 ? 30 : 31);
	}
}
