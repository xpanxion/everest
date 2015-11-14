package com.xpanxion.wallboard.rest.dto.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds stock ticker information
 * 
 * @author apofahl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockInfo {

	private String change;
	private String changePercent;
	private String lastPrice;
	private String name;
	private long retrievalTime;
	private String symbol;

	/**
	 * @return the change
	 */
	public String getChange() {
		return this.change;
	}

	/**
	 * @return the changePercent
	 */
	public String getChangePercent() {
		return this.changePercent;
	}

	/**
	 * @return the lastPrice
	 */
	public String getLastPrice() {
		return this.lastPrice;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the retrevalTime
	 */
	@JsonIgnore()
	public long getRetrievalTime() {
		return this.retrievalTime;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return this.symbol;
	}

	/**
	 * @param change
	 *            the change to set
	 */
	@JsonProperty("Change")
	public void setChange(String change) {
		this.change = change;
	}

	/**
	 * @param changePercent
	 *            the changePercent to set
	 */
	@JsonProperty("ChangePercent")
	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}

	/**
	 * @param lastPrice
	 *            the lastPrice to set
	 */
	@JsonProperty("LastPrice")
	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param retrevalTime
	 *            the retrevalTime to set
	 */
	@JsonIgnore()
	public void setRetrievalTime(long retrevalTime) {
		this.retrievalTime = retrevalTime;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	@JsonProperty("Symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
