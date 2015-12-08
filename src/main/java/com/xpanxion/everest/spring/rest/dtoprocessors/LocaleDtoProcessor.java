package com.xpanxion.everest.spring.rest.dtoprocessors;

import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import com.xpanxion.everest.controllers.data.ApiDataLocaleController;
import com.xpanxion.everest.dto.locale.Locale;
import com.xpanxion.everest.util.RestResourceUtils;

@Component
public class LocaleDtoProcessor {
	
	@Bean
	public ResourceProcessor<Resource<Locale>> localeProcessor() {
		
		return new ResourceProcessor<Resource<Locale>>() {
			
			@Override
			public Resource<Locale> process(Resource<Locale> resource) {
				final Locale locale = resource.getContent();
				
				// Add the custom links
				resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ApiDataLocaleController.class)
						.getWeather(locale.getId())).withRel("weather"));
				resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ApiDataLocaleController.class)
						.getNews(locale.getId())).withRel("news"));
				resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ApiDataLocaleController.class)
						.getStocks(locale.getId())).withRel("stocks"));
				
				// TODO - Why is locale added in the first place?
				RestResourceUtils.removeAllLinksByRel(resource.getLinks(), "locale");
				
				return resource;
			}
		};
	}
}
