package hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * testarla non come Spring, ma inserendo prima il nome dell'app:
 * http://localhost:8080/ciao/zucchemberg
 *
 * pathInfo sarà "/zucchemberg"
 * splittando grazie al carattere "/" avremo:
 * pathPart[0] = null
 * pathPart[1] = "zucchemberg"
 */
@WebServlet(urlPatterns = "/ciao/*", loadOnStartup = 1)
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String pathVariable = pathParts[1]; // {value}

        if("inner".equals(pathVariable)){  //works but cannot create JSPs
           // request.setAttribute("attributo", "AncheNonUnaStringa");
            request.getRequestDispatcher("/WEB-INF/cocomero.html").forward(request, response);
        }else if("outer".equals(pathVariable)){
            //Map<String, String> mappa = new HashMap<String, String>();
            //mappa.put("primo", "primoValore");
            //mappa.put("secondo", "secondoValore");
            //request.setAttribute("mappa", mappa);
            request.getRequestDispatcher("/pilla.html").forward(request, response);
        }else if("redirect".equals(pathVariable)){
            response.sendRedirect("/ServletDirectly/hello?nome=Alessandro");
        }else{
            response.getWriter().print("{'message': 'Ciao " + pathVariable +"!'}");
        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        this.doGet(request, response);
    }
}