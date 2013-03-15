package thrifty.api.controller;

import java.util.List;

import thrifty.api.model.Entity;
import thrifty.api.service.Service;

public class ServiceController implements GETController {
	
	private Service service;

	public Entity get(Integer id) {
		return service.read(id);
	}

	public List<Entity> list() {
		return service.list();
	}

}
