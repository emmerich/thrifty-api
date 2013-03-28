package thrifty.api.update.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thrifty.api.update.refresh.WikiaRefresher;

@Component
public class WikiaRefreshSchedule extends DailyRefreshSchedule {
	
	@Autowired
	public WikiaRefreshSchedule(WikiaRefresher refresher) {
		super(refresher);
	}

	@Override
	public boolean needsRefresh() {
		return true;
	}

}
