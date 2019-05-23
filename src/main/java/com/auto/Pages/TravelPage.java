package com.auto.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.Base.Log;
import com.auto.Base.SeleniumDriver;
import com.auto.Base.Verify;
import com.google.common.collect.Ordering;

public class TravelPage extends SeleniumDriver {

	private String blk_card = "//*[@class='col-sm-4 card-full']";
	private String card_title = "//*[@class='col-sm-4 card-full']//*[@class='name']";
	private String card_price = "//*[@class='col-sm-4 card-full']//*[@class='value']";
	private String chk_FilterFPG = "//*[@data-filter-name='FPG Insurance']";
	private String radio_filterPromosOnly = "//*[@for='gb_radio_18']";
	private String radio_filterShowAll = "//*[@for='gb_radio_17']";
	private String radio_softPromotion = "//*[@for='gb_radio_2']";
	private String radio_softPriceLowToHigh = "//*[@for='gb_radio_3']";
	private String btn_SeeMore = "//*[@class='btn-ripple more']";

	private String dropdown_destionation = "//*[@class='select-component']";
	private String dropdown_country = "//*[@class='dropdown-menu open']//li";

	private String calendar_startDay = "//*[@name='dates-startdate']";
	private String calendar_endDay = "//*[@name='dates-enddate']";

	private String calendar_today = "//*[@class='datepicker-days']//*[@class='today']";

	private String select_priceMin = "//*[@class='slider-handle min-slider-handle round']";

	private String page_noResult = "//*[@class='no-results text-center']";
	private String btn_resetFilter = "//*[@id='collapseFilterBtn']//*[contains(@class,'clear-all')]";
	private String btn_cancel = "//*[@id='step-0']//*[@data-role='cancel']";

	public TravelPage(WebDriver driver) {
		super(driver);
		// verify TravelPage loaded
		waitElementPresent(blk_card);
	}

	public void closeTutorial() {
		sleep(2000);
		click(btn_cancel);
	}

	public TravelPage verifyAtLeast3CardsDisplay() {
		List<WebElement> list_cards = findElements(blk_card);
		boolean isTrue = list_cards.size() >= 3;
		Verify.verifyTrue(isTrue, "Verify at least 3 block cards are present");
		return this;
	}

	public TravelPage filterPromotions(String promotions) {
		Log.info("Filter promotions by: " + promotions);
		switch (promotions) {
		case "Show all":
			click(radio_filterShowAll);
			break;
		case "Promos only":
			click(radio_filterPromosOnly);
			break;
		}
		return this;
	}

	public TravelPage soft(String option) {
		Log.info("Soft by: " + option);
		switch (option) {
		case "Promotion":
			click(radio_softPromotion);
			break;
		case "Price: Low to High":
			click(radio_softPriceLowToHigh);
			break;
		}
		return this;
	}

	public TravelPage filterInsurers(String insurers) {
		closeTutorial();
		Log.info("Filter insurers by: " + insurers);
		switch (insurers) {
		case "FPG Insurance":
			String checkbox_locator = chk_FilterFPG + "//input";
			if (!isSelected(checkbox_locator)) {
				click(chk_FilterFPG);
			}
			break;
		case "Malayan Insurance ":
			// implement later
			break;
		case "Pacific Cross ":
			// implement later
			break;
		case "Pioneer Insurance ":
			// implement later
			break;
		}
		sleep(2000);
		return this;
	}

	public TravelPage openSeeMore() {
		sleep(1000);
		click(btn_SeeMore);
		return this;
	}

	public TravelPage selectDestination(String country) {
		Log.info("Select country from dropdown list");
		click(dropdown_destionation);
		List<WebElement> list_country = findElements(dropdown_country);
		for (int i = 0; i < list_country.size(); i++) {
			String country_xpath = dropdown_country + "[" + i + 1 + "]";
			String country_name = getText(country_xpath);
			if (country_name.equalsIgnoreCase(country)) {
				click(country_xpath);
				break;
			}
		}
		return this;
	}

	public TravelPage selectPriceRange() {
		Log.info("Select price");
		moveElemenTo(select_priceMin, 50, 0);
		return this;
	}

	public TravelPage selectToday() {
		Log.info("Select today");
		sleep(1000);
		click(calendar_startDay);
		sleep(1000);
		click(calendar_today);
		sleep(1000);
		click(calendar_endDay);
		sleep(1000);
		click(calendar_today);
		return this;
	}

	public TravelPage verifyNoResultPagePresent() {
		sleep(1000);
		boolean isTrue = isPresent(page_noResult);
		Verify.verifyTrue(isTrue, "Verify no results found!");
		return this;
	}

	public TravelPage clearFilter() {
		Log.info("Clear all filter");
		click(btn_resetFilter);
		sleep(1000);
		return this;
	}

	public TravelPage verifyFilterInsurers(String expected) {
		List<WebElement> titles = findElements(card_title);
		boolean isTrue = true;
		for (int i = 1; i <= titles.size(); i++) {
			String title_path = "(" + card_title + ")[" + i + "]";
			String title = getText(title_path);
			if (!title.contains(expected)) {
				isTrue = false;
				break;
			}
		}
		Verify.verifyTrue(isTrue, "Verify filter by Insurers: " + expected);
		return this;
	}

	public TravelPage verifySortPriceLowToHigh() {
		List<WebElement> element_prices = findElements(card_price);
		List<Integer> list_price = new ArrayList<Integer>();
		for (int i = 1; i <= element_prices.size(); i++) {
			String price_path = "(" + card_price + ")[" + i + "]";
			String price = getText(price_path).replaceAll(",", "");
			list_price.add(Integer.valueOf(price));
		}
		boolean isTrue = Ordering.natural().isOrdered(list_price);
		Verify.verifyTrue(isTrue, "Verify filter price low to high");
		return this;
	}
}
