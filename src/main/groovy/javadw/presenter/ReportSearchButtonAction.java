package javadw.presenter;

import java.util.ArrayList;
import java.util.List;

import javadw.model.Report;
import javadw.presenter.interfaces.IReportSearchable;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class ReportSearchButtonAction implements Button.ClickListener, ValueChangeListener<String> {

	private static final long serialVersionUID = 1L;

	private IReportSearchable view;

	public ReportSearchButtonAction(IReportSearchable view) {
		this.view = view;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		String sc = view.getSearchCriteria();
		filter(sc);

	}
	
	private void filter(String sc) {
		if (sc.length() == 0) {
			view.setReports(Init.getReports());
		} else {
			List<Report> filtered = new ArrayList<Report>();
			for (Report r : Init.getReports()) {
				if (r.getName().toLowerCase().contains(sc.toLowerCase())) {
					filtered.add(r);
				}
			}
			try {
				view.setReports(filtered);
			} catch(IllegalArgumentException e) {
				view.notifyNoResult(); 
			}
		}
	}

	@Override
	public void valueChange(ValueChangeEvent<String> event) {
		filter(event.getValue());		
	}

}
