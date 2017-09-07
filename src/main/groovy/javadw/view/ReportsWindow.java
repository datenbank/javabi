package javadw.view;

import java.io.File;
import java.util.List;

import javadw.model.Report;
import javadw.presenter.ReportParametersLinkAction;
import javadw.presenter.ReportSearchButtonAction;
import javadw.presenter.interfaces.IReportParamable;
import javadw.presenter.interfaces.IReportSearchable;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

public class ReportsWindow extends VerticalLayout implements IReportSearchable, IReportParamable {

	private static final long serialVersionUID = 1L;
	
	private TextField searchField = new TextField();
	private Button searchButton = new Button(Messages.getString("ReportsWindow.SearchButtonLabel"));
	private Grid<Report> grid = new Grid<Report>();
	
	
	List<Report> reports;

	public ReportsWindow(String basepath) {
		
		// +Title section start
		HorizontalLayout logoLayout = new HorizontalLayout();
		FileResource logo = new FileResource(new File(basepath
				+ "/WEB-INF/classes/logo.png")); 
		Image logoImg = new Image(null,logo);
		logoLayout.addComponent(logoImg);
		addComponent(logoLayout);
		// -Title section end

		// +Search section start
		HorizontalLayout searchLayout = new HorizontalLayout();
		searchField.setWidth("300px"); 
		searchLayout.addComponent(searchField);
		searchLayout.addComponent(searchButton);
		addComponent(searchLayout);
		searchButton.addClickListener(new ReportSearchButtonAction(this));
		searchField.addValueChangeListener(new ReportSearchButtonAction(this));
		// -Search section end
		
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/classes/run.png")); //$NON-NLS-1$
	
		// +Grid section start		
		grid.addComponentColumn(report -> {
			
			Image img = new Image(Messages.getString("ReportsWindow.ReportParametersLinkImageLabel"), resource); 
			img.addClickListener(new ReportParametersLinkAction(this, report));	
			return img;
							
			
		}).setWidth(75).setMaximumWidth(75).setSortable(false);
		
		grid.addColumn(Report::getDisplayName).setWidth(300).setCaption(Messages.getString("ReportsWindow.ReportColumnLabel")); 
		grid.addColumn(Report::getGroup).setCaption(Messages.getString("ReportsWindow.GroupColumnLabel")); 
		grid.setWidth("100%"); //$NON-NLS-1$
		grid.setSelectionMode(SelectionMode.SINGLE);
		addComponent(grid);
		// -Grid section end
		
	}
	
	public void setReports(List<Report> reports) {
		this.reports = reports;
		grid.setItems(reports);
		grid.setHeightByRows(reports.size());
	}
	
	public String getSearchCriteria() {
		return searchField.getValue();
	}
	
	public void addWindow(Report report) {
		ReportParametersWindow pw = new ReportParametersWindow(report);
		getUI().addWindow(pw);
	}
	
	public void notifyNoResult() {
		new Notification(Messages.getString("ReportsWindow.InfoNotificationCaption"), Messages.getString("ReportsWindow.SearchButtonInfoNotification")).show(getUI().getPage());
	}

}
