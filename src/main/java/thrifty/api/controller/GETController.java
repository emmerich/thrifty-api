package thrifty.api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thrifty.api.model.Entity;

/**
 * A Controller that provides the GET HTTP request
 * type.
 * 
 * @author emmerich
 */
@Controller
public interface GETController {
	
	/**
	 * Returns the Entity at the given ID.
	 * @param id the ID to look up.
	 * @return the Entity matching that ID.
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Entity get(Integer id);
	
	/**
	 * Returns a list of all Entities handled by
	 * this Controller.
	 * 
	 * @return all Entities handled by this Controller.
	 */
    @RequestMapping(method = RequestMethod.GET)
	public List<Entity> list();
}
