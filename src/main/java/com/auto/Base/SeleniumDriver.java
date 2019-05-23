package com.auto.Base;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDriver {

	WebDriver driver;

	public SeleniumDriver(WebDriver driver) {
		this.driver = driver;
	}

	public By locatorType(String locator, String type) {
		switch (type) {
		case "xpath":
			return By.xpath(locator);
		case "css":
			return By.cssSelector(locator);
		case "name":
			return By.name(locator);
		case "id":
			return By.id(locator);
		case "linkText":
			return By.linkText(locator);
		case "class":
			return By.className(locator);
		default:
			return By.xpath(locator);

		}
	}

	public WebElement findElement(String locator) {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(locator));
		} catch (NoSuchElementException e) {
			Log.fail("Element with " + locator + " Not Found!");
		} catch (Exception e) {
			Log.fail("Find element with" + locator + "FAIL!");
		}
		return element;
	}

	public WebElement findElement(String locator, String type) {
		WebElement element = null;
		try {
			element = driver.findElement(locatorType(locator, type));
		} catch (NoSuchElementException e) {
			Log.fail("Element with " + locator + " Not Found!");
		} catch (Exception e) {
			Log.fail("Find element with" + locator + "FAIL!");
		}
		return element;
	}

	public List<WebElement> findElements(String locator) {
		List<WebElement> element = null;
		try {
			element = driver.findElements(By.xpath(locator));
		} catch (NoSuchElementException e) {
			Log.fail("Elements with " + locator + " Not Found!");
		} catch (Exception e) {
			Log.fail("Find elements with" + locator + "FAIL!");
		}
		return element;
	}

	public void click(String locator) {
		try {
			findElement(locator).click();
		} catch (Exception e) {
			Log.fail("Click Element with " + locator + " FAIL!");
		}
	}

	public void click(String locator, String type) {
		try {
			findElement(locator, type).click();
		} catch (Exception e) {
			Log.fail("Click Element with " + locator + " FAIL!");
		}
	}

	public String getText(String locator) {
		String text = "";
		try {
			text = findElement(locator).getText();
		} catch (Exception e) {
			Log.fail("Get text Element " + locator + " FAIL!");
		}
		return text;
	}

	public String getText(String locator, String type) {
		String text = "";
		try {
			text = findElement(locator, type).getText();
		} catch (Exception e) {
			Log.fail("Get text Element " + locator + " FAIL!");
		}
		return text;
	}

	public void sendKeys(String locator, String text) {
		try {
			findElement(locator).sendKeys(text);
		} catch (Exception e) {
			Log.fail("Set text Element " + locator + " FAIL!");
		}
	}

	public void sendKeys(String locator, String text, String type) {
		try {
			findElement(locator, type).sendKeys(text);
		} catch (Exception e) {
			Log.fail("Set text Element " + locator + " FAIL!");
		}
	}

	public void waitElementPresent(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}

	public void waitElementPresent(String locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}

	public void waitElementPresent(String locator, String type) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(locatorType(locator, type)));
	}

	public boolean isSelected(String locator) {
		boolean isSelected = false;
		try {
			isSelected = findElement(locator).isSelected();
		} catch (Exception e) {
			Log.fail("Check Element checked " + locator + " FAIL!");
		}
		return isSelected;
	}

	public void sleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void moveElemenTo(String locator, int to_X, int to_Y) {
		WebElement ele = findElement(locator);
		Point point = ele.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		Actions move = new Actions(driver);
		move.dragAndDropBy(ele, xcord + to_X, ycord + to_X).perform();
	}

	public boolean isPresent(String locator) {
		boolean isPresent = false;
		try {
			findElement(locator);
			isPresent = true;
		} catch (NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}
}
