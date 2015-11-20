package com.xpanxion.wallboard.rest.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;

import com.xpanxion.wallboard.rest.dto.framework.CustomLink;

public class RestResourceUtils {

	public static final Logger LOG = LoggerFactory.getLogger(RestResourceUtils.class);
	
	private RestResourceUtils() {}
	
	public static CustomLink buildCustomLink(Link link, String method) {
		final CustomLink customLink = new CustomLink(link);
		customLink.setMethod(method);
		return customLink;
	}
	
	/**
	 * 
	 * @param links
	 * @param rel
	 * @return
	 */
	public static List<Link> removeAllLinksByRel(List<Link> links, String rel) {
		final List<Link> removals = new ArrayList<>();
		for(Link link : links) {
			if (link.getRel().equals(rel)) {
				removals.add(link);
			}
		}
		links.removeAll(removals);
		return links;
	}
	
}
