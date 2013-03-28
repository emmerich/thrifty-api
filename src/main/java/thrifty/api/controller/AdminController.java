package thrifty.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import thrifty.api.update.scheduler.WikiaRefreshSchedule;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private WikiaRefreshSchedule refresher;
	
	@RequestMapping("/refresh")
	public void refresh() {
		refresher.scheduledRefresh();
	}

}
