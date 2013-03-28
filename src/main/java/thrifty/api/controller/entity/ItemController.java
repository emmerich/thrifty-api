package thrifty.api.controller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import thrifty.api.controller.ServiceController;
import thrifty.api.service.entity.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController extends ServiceController {

    @Autowired
    public ItemController(ItemService service) {
        super(service);
    }
}
