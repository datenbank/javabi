package javadw.presenter;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import com.vaadin.server.VaadinServlet;

@WebServlet(
    asyncSupported=false,
    urlPatterns={"/*","/VAADIN/*"},
    initParams={
        @WebInitParam(name="ui", value="javadw.presenter.JavadwUI")
        ,@WebInitParam(name="productionMode", value="true")
    })
public class JavadwServlet extends VaadinServlet {

	private static final long serialVersionUID = 1L; }
