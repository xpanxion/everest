package com.xpanxion.wallboard.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.wallboard.rest.controllers.service.ServiceController;

@RestController
public class BasePathController extends BaseController {
	
	@RequestMapping(value = BASE_PATH, method = RequestMethod.GET)
	public ResourceSupport getBasePath(HttpServletRequest request) {
		final ResourceSupport resource = new ResourceSupport();
		resource.add(new Link(request.getRequestURL() + "/data", "data"));
		
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ServiceController.class)
				.getServices()).withRel("service"));
		
		return resource;
	}
}
