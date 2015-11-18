package com.xpanxion.wallboard.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xpanxion.wallboard.rest.config.security.SecurityProperties.SecurityRoles;
import com.xpanxion.wallboard.rest.dto.kudos.Kudo;
import com.xpanxion.wallboard.rest.services.KudoService;

@RestController
public class KudosController {

	@Autowired
	private KudoService kudoService;
	
	@RequestMapping(value = "/api/{version}/{rendition}/kudos", method = RequestMethod.GET)
	public List<Kudo> getKudos() {
		return this.kudoService.getAllKudos();
	}
	
	@RequestMapping(value = "/api/{version}/{rendition}/kudos/requests/add", method = RequestMethod.POST)
	public void requestToAddKudo(@RequestBody Kudo kudo) {
		// TODO
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/api/{version}/{rendition}/kudos/requests/edit", method = RequestMethod.POST)
	public void requestToEditKudo(@RequestBody Kudo kudo) {
		// TODO
		throw new UnsupportedOperationException();
	}
	
	@Secured({ SecurityRoles.API_ADMIN })
	@RequestMapping(value = "/api/{version}/{rendition}/kudos/add", method = RequestMethod.POST)
	public Kudo addKudo(@RequestBody Kudo kudo) {
		return this.kudoService.createNewKudo(kudo);
	}
	
	@RequestMapping(value = "/api/{version}/{rendition}/kudos/{kudoId}", method = RequestMethod.GET)
	public Kudo getKudo(@PathVariable Long kudoId) {
		return this.kudoService.getKudoById(kudoId);
	}
	
	@Secured({ SecurityRoles.API_ADMIN })
	@RequestMapping(value = "/api/{version}/{rendition}/kudos/{kudoId}", method = RequestMethod.DELETE)
	public void deleteKudo(@PathVariable Long kudoId) {
		this.kudoService.deleteKudoById(kudoId);
	}
	
	@RequestMapping(value = "/api/{version}/{rendition}/kudos/{kudoId}", method = RequestMethod.PUT)
	public Kudo editKudo(@PathVariable Long kudoId, @RequestBody Kudo kudo) {
		return this.kudoService.updateKudo(kudoId, kudo);
	}
}
