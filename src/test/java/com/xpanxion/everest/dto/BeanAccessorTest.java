package com.xpanxion.everest.dto;

import com.xpanxion.everest.dto.employee.Employee;
import com.xpanxion.everest.dto.framework.CustomLink;
import com.xpanxion.everest.dto.kudos.Kudo;
import com.xpanxion.everest.dto.locale.Locale;
import com.xpanxion.everest.dto.locale.LocaleAlias;
import com.xpanxion.everest.dto.locale.LocaleTheme;
import com.xpanxion.everest.dto.news.GoogleNewsEntry;
import com.xpanxion.everest.dto.news.NewsContent;
import com.xpanxion.everest.dto.openWeather.*;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.system.ApiToken;
import com.xpanxion.everest.dto.system.SystemRole;
import com.xpanxion.everest.dto.weather.DayAfter;
import com.xpanxion.everest.dto.weather.Weather;
import org.junit.Assert;
import org.junit.Test;

import static com.xpanxion.everest.testutils.BeanAccessorUtils.testBeanClass;

public class BeanAccessorTest {

	private StringBuilder testFailures;
	
	@Test
	public void testBeanAccessors() {
		this.testFailures = new StringBuilder();

		test(Employee.class);
		test(CustomLink.class);
		test(Kudo.class);
		test(Locale.class);
		test(LocaleAlias.class);
		test(LocaleTheme.class);
		test(GoogleNewsEntry.class);
		test(NewsContent.class);
		test(StockInfo.class);
		test(ApiToken.class);
		test(SystemRole.class);
		test(DayAfter.class);
		test(Weather.class);
		test(City.class);
		test(Clouds.class);
		test(Coord.class);
		test(Forecast.class);
		test(ForecastResponse.class);
		test(Main.class);
		test(Rain.class);
		test(Snow.class);
		test(com.xpanxion.everest.dto.openWeather.Weather.class);
		test(Wind.class);
		
		if (testFailures.length() > 0) {
			Assert.fail(testFailures.toString());
		}
	}
	
	private void test(Class<?> clazz) {
		this.testFailures.append(testBeanClass(clazz));
	}
}
