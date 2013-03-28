package thrifty.api.update.scheduler;

public interface RefreshSchedule {
	
	public boolean needsRefresh();
	public void performRefresh();
	
	public void scheduledRefresh();
	
}
