package thrifty.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thrifty.api.dao.patch.PatchUpdateDataAccess;
import thrifty.api.model.patch.PatchUpdate;

@Controller
@RequestMapping("/patch")
public class PatchController {
	
	private PatchUpdateDataAccess currentPatchDataAccess;

	@RequestMapping(method = RequestMethod.GET)
	public PatchUpdate get() {
		return currentPatchDataAccess.mostRecentPatch();
	}
}
