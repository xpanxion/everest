package com.xpanxion.everest.dto;

import static com.xpanxion.everest.testutils.BeanAccessorUtils.testBeanClass;

import org.junit.Assert;
import org.junit.Test;

import com.xpanxion.everest.dto.employee.Employee;
import com.xpanxion.everest.dto.framework.CustomLink;
import com.xpanxion.everest.dto.kudos.Kudo;
import com.xpanxion.everest.dto.locale.Locale;
import com.xpanxion.everest.dto.locale.LocaleAlias;
import com.xpanxion.everest.dto.locale.LocaleTheme;
import com.xpanxion.everest.dto.news.GoogleNewsEntry;
import com.xpanxion.everest.dto.news.NewsContent;
import com.xpanxion.everest.dto.stock.StockInfo;
import com.xpanxion.everest.dto.system.ApiToken;
import com.xpanxion.everest.dto.system.SystemRole;
import com.xpanxion.everest.dto.weather.DayAfter;
import com.xpanxion.everest.dto.weather.Weather;
import com.xpanxion.everest.dto.weather.WeatherCondition;

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
		test(WeatherCondition.class);
		
		if (testFailures.length() > 0) {
			Assert.fail(testFailures.toString());
		}
	}
	
	private void test(Class<?> clazz) {
		this.testFailures.append(testBeanClass(clazz));
	}
}
