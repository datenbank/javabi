package javadw.presenter.interfaces;

import java.util.List;

public interface IReportRunnable {
	public List<String[]> getParameters();
	public boolean getPdfChecked();
	public void setLocation(String location);
}
