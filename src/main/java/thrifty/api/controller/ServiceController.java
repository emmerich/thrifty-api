package thrifty.api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import thrifty.api.model.Entity;
import thrifty.api.service.Service;

@Controller
public class ServiceController implements GETController {
	
	protected Service service;

    @Override
	public @ResponseBody Entity get(@PathVariable("id") Integer id) {
		return service.read(id);
	}

    @Override
	public @ResponseBody List<Entity> list() {
		return service.list();
	}

}
