package javadw.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javadw.model.Report;
import javadw.presenter.ReportRunButtonAction;
import javadw.presenter.interfaces.IReportRunnable;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class ReportParametersWindow extends Window implements IReportRunnable {
	
	private static final long serialVersionUID = 1L;

	private FormLayout form = new FormLayout();

	private CheckBox pdfCheckBox = new CheckBox("pdf");
	
	private List<TextField> textFields = new ArrayList<TextField>();
	
	private Report report;
	
	public ReportParametersWindow(Report report) {
		super(report.getDisplayName()+" "+Messages.getString("ReportParametersWindow.WindowTitleExtension")); 
		this.report = report;	
		setContent(form);
		center();
        setModal(true);
        setWidth("500px");
        
        Iterator<Entry<String, Object>> it = report.getParameters().entrySet().iterator();
	    
		while (it.hasNext()) {
	        Map.Entry<String, Object> pair = (Map.Entry<String, Object>)it.next();	        
	        addParameter(pair.getKey().toString(), pair.getValue().toString());
	        
	    }
		pdfCheckBox.setValue(true);
		form.addComponent(pdfCheckBox);
		addButton();
	}
	
	private void addParameter(String name, String value) {
		TextField tf = new TextField(name);
		tf.setValue(value);
        tf.setRequiredIndicatorVisible(true);
        form.addComponent(tf);
        
        textFields.add(tf);
   
	}
	
	private void addButton() {
		Button run = new Button(Messages.getString("ReportParametersWindow.RunButtonLabel"));
        run.addClickListener(new ReportRunButtonAction(this, report));
        form.addComponent(run);
	}
	
	public List<String[]> getParameters() {
		List<String[]> p = new ArrayList<String[]>();
		for(TextField tf : textFields) {
			p.add(new String[]{tf.getCaption(), tf.getValue()});
		}
				
		return p;
	}
	
	public Report getReport() {
		return report;
	}
	
	public boolean getPdfChecked() {
		return pdfCheckBox.getValue();
		
	}
	
	public void setLocation(String location) {
		getUI().getPage().setLocation(location);
	}

}
