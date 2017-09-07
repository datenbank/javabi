package javadw.presenter;

import javadw.view.ReportsWindow;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.UI;
import com.vaadin.annotations.Theme;


@Theme("Javadw")
public class JavadwUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
			
		String basepath = VaadinService.getCurrent().getBaseDirectory()
				.getAbsolutePath();
	
		
		Init.init(basepath + "/WEB-INF/classes/");
		
		ReportsWindow rw = new ReportsWindow(basepath);
		rw.setReports(Init.getReports());
		
		setContent(rw);		


	}	
		
}
