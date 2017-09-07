package javadw.presenter.interfaces;

import javadw.model.Report;
import java.util.List;

public interface IReportSearchable {

	public String getSearchCriteria();
	public void setReports(List<Report> reports);
	public void notifyNoResult();
}
