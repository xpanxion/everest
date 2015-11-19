package com.xpanxion.wallboard.rest.controllers.service;

import java.util.ArrayList;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.wallboard.rest.controllers.BaseController;
import com.xpanxion.wallboard.rest.util.RestResourceUtils;

@RestController
public class ServiceController extends BaseController {
	
	@RequestMapping(value = BASE_PATH + "/service", method = RequestMethod.GET)
	public ResourceSupport getServices() {
		final ResourceSupport resource = new ResourceSupport();
		
		resource.add(RestResourceUtils.buildCustomLink(ControllerLinkBuilder.linkTo(ControllerLinkBuilder
				.methodOn(StockInfoServiceController.class)
				.getStock(":STOCK_SYMBOL")).withRel("stocks"), "post"));
		
		resource.add(RestResourceUtils.buildCustomLink(ControllerLinkBuilder.linkTo(ControllerLinkBuilder
				.methodOn(StockInfoServiceController.class)
				.getStocks(new ArrayList<String>())).withRel("stocks"), "post"));
		
		return resource;
	}
	
}
