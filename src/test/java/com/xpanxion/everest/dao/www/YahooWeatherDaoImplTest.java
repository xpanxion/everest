package com.xpanxion.everest.dao.www;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.xpanxion.everest.dto.weather.Weather;

public class YahooWeatherDaoImplTest {

	private static final String ROSWELL_LOC = "88203";
	private YahooWeatherDaoImpl testee;

	@Before
	public void setUp() throws Exception {
		this.testee = new YahooWeatherDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBadURL() {
		this.testee.setWeatherURLPattern("http://0.0.0.0");
		Assert.assertNull(this.testee.getWeather(ROSWELL_LOC));
	}

	@Test
	public void testGetWeatherForBahamasInitalAndCached() {
		this.testee.setCacheTime(Integer.MAX_VALUE);
		long beginTime = System.currentTimeMillis();
		Weather nonCached = this.testee.getWeather(ROSWELL_LOC);
		long endTime = System.currentTimeMillis();
		Weather cached = this.testee.getWeather(ROSWELL_LOC);

		Assert.assertTrue(nonCached.getCity().equalsIgnoreCase("roswell"));
		Assert.assertTrue(beginTime <= nonCached.getRetrevalTime()
				&& nonCached.getRetrevalTime() <= endTime);
		Assert.assertTrue(EqualsBuilder.reflectionEquals(nonCached, cached,
				true));

	}

	@Test
	public void testGetWeatherForRoswellInitalAndExpiredCache()
			throws InterruptedException {
		this.testee.setCacheTime(0);
		long beginTime1 = System.currentTimeMillis();
		Weather nonCached1 = this.testee.getWeather(ROSWELL_LOC);
		long endTime1 = System.currentTimeMillis();

		Thread.sleep(1000);

		long beginTime2 = System.currentTimeMillis();
		Weather nonCached2 = this.testee.getWeather(ROSWELL_LOC);
		long endTime2 = System.currentTimeMillis();

		Assert.assertTrue(nonCached1.getCity().equalsIgnoreCase("roswell"));
		Assert.assertTrue(beginTime1 <= nonCached1.getRetrevalTime()
				&& nonCached1.getRetrevalTime() <= endTime1);

		Assert.assertTrue(nonCached2.getCity().equalsIgnoreCase("roswell"));
		Assert.assertTrue(beginTime2 <= nonCached2.getRetrevalTime()
				&& nonCached2.getRetrevalTime() <= endTime2);

		Assert.assertFalse(EqualsBuilder.reflectionEquals(nonCached1,
				nonCached2, true));

	}

	@Test
	public void testMalformedURL() {
		this.testee.setWeatherURLPattern("NOT A URL PATTERN");
		Assert.assertNull(this.testee.getWeather(ROSWELL_LOC));
	}

	@Test
	public void testNonParsableFeed() {
		this.testee.setWeatherURLPattern("http://www.google.com");
		Assert.assertNotNull(this.testee.getWeather(ROSWELL_LOC));
	}
}
