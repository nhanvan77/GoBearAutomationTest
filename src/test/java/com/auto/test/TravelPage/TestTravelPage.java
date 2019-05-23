package com.auto.test.TravelPage;

import org.testng.annotations.Test;

import com.auto.Base.TestBase;
import com.auto.Pages.HomePage;
import com.auto.Pages.TravelPage;

public class TestTravelPage extends TestBase {

	/*Test step
	 * 1. Open Travel page
	 * 2. Verify at least 3 card display
	 * 3. Verify filter promotions: Promos only => expected: no page display
	 * 4. Verify filter Insurers: FPG Insurance => expected: all title is FPG Insurance
	 * 5. Verify soft price low to high => expected: price sorted low to high
	 * 6. Select destination => expected: function work
	 * 7. Select date => expected: function work
	 * 8. Select price range by price bar under see more => expected: function work
	 */
	@Test
	public void testTravelPage() {
		driver.get("https://www.gobear.com/ph?x_session_type=UAT");
		HomePage home = new HomePage(driver);
		home.openTravelSection();
		TravelPage travel = new TravelPage(driver);
		travel.verifyAtLeast3CardsDisplay().filterPromotions("Promos only").verifyNoResultPagePresent().clearFilter()
				.filterInsurers("FPG Insurance").verifyFilterInsurers("FPG Insurance").clearFilter()
				.soft("Price: Low to High").verifySortPriceLowToHigh().selectDestination("Albania").selectToday()
				.openSeeMore().selectPriceRange();
	}
}
