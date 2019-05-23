package com.auto.Pages;

import org.openqa.selenium.WebDriver;

import com.auto.Base.SeleniumDriver;

public class HomePage extends SeleniumDriver{
	private String tab_insurance = "//*[@data-gb-name='Insurance']";
	private String tab_travel = "//*[@data-gb-name='Travel']";
	private String btn_showMyResults = "//*[@id='Travel']//*[@name='product-form-submit']";
	
	public HomePage(WebDriver driver) {
		super(driver);
		waitElementPresent(tab_insurance);
	}
	
	public HomePage openTravelSection() {
		openInsuranceTab();
		openTravelTab();
		showResult();
		return this;
	}
	
	public HomePage openInsuranceTab() {
		click(tab_insurance);
		return this;
	}
	
	public HomePage openTravelTab() {
		click(tab_travel);
		return this;
	}
	
	public HomePage showResult() {
		sleep(1000);
		click(btn_showMyResults);
		return this;
	}
	
}
