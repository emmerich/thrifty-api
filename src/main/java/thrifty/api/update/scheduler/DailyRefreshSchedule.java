package thrifty.api.update.scheduler;

import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import thrifty.api.update.refresh.Refresher;

@EnableScheduling
@Component
public abstract class DailyRefreshSchedule implements RefreshSchedule {
	
	private Refresher refresher;
	private static Logger LOG = Logger.getLogger(DailyRefreshSchedule.class);
	
	public DailyRefreshSchedule(Refresher refresher) {
		this.refresher = refresher;
	}

	@Override
	public void performRefresh() {
		refresher.refresh();
	}

	@Scheduled(cron = "0 16 12 * * ?")
	@Override
	public void scheduledRefresh() {
		LOG.debug("Scheduled refresh triggered.");
		
		if(needsRefresh()) {
			LOG.debug("We require a refresh, performing refresh.");
			performRefresh();
		}
	}

}
