/**
 * 
 */
package com.xpanxion.wallboard.rest.dto.news;

/**<p> 
 *
 * @author tony
 *
 */
public class GoogleNewsConstants {

	public static final String NUM_ARTICLES_KEY = "num";
	public static final String QUERY_KEY = "q";
	
	public enum OutputType { 
		RSS("rss"),
		ATOM("atom");
		private final String value;
		private String key = "output";
		OutputType(String value) {
			this.value = value;
		}
		public String getValue() {
			return this.value;
		}
		public String getKey() {
			return this.key;
		}
	}
	
	public enum RetrieveType {
		ALL("all"),
		QUOTES("q"),
		IMAGES("i"),
		BLOGS("b");
		private final String value;
		private String key = "cf";
		RetrieveType(String value) {
			this.value = value;
		}
		public String getValue() {
			return this.value;
		}
		public String getKey() {
			return this.key;
		}
	}
	
	public enum Language {
		US("us"),
		EN("en");
		private final String value;
		private String key = "hl";
		Language(String value) {
			this.value = value;
		}
		public String getValue() {
			return this.value;
		}
		public String getKey() {
			return this.key;
		}
	}
	
	public enum Edition {
		US("us");
		private final String value;
		private String key = "ned";
		Edition(String value) {
			this.value = value;
		}
		public String getValue() {
			return this.value;
		}
		public String getKey() {
			return this.key;
		}
	}
	
	/*
	 * Not sure what this value does...
	 */
	public enum PzType {
		ZERO("0"),
		ONE("1");
		private final String value;
		private String key = "pz";
		PzType(String value) {
			this.value = value;
		}
		public String getValue() {
			return this.value;
		}
		public String getKey() {
			return this.key;
		}
	}
}
