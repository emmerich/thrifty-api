package thrifty.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thrifty.api.dao.patch.CurrentPatchDataAccess;
import thrifty.api.model.patch.PatchNumber;

@Controller
@RequestMapping("/patch")
public class PatchController {
	
	private CurrentPatchDataAccess currentPatchDataAccess;

	@RequestMapping(method = RequestMethod.GET)
	public PatchNumber get() {
		return currentPatchDataAccess.currentPatch();
	}
}
