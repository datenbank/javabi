package javadw.presenter;

import javadw.model.Report;
import javadw.presenter.interfaces.IReportParamable;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;

public class ReportParametersLinkAction implements ClickListener {

	private static final long serialVersionUID = 1L;

	private Report report;
	private IReportParamable view;
	
	public ReportParametersLinkAction(IReportParamable view, Report report) {
		this.report = report;
		this.view = view;
	}

	@Override
	public void click(ClickEvent event) {
		view.addWindow(report);
		
	}

}
