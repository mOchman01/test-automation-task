package com.akamai.automation.tests.gui

import com.akamai.automation.tests.gui.pages.AkamaiPage
import com.akamai.automation.tests.gui.pages.SearchResultsPage
import com.akamai.automation.tests.gui.utils.LogSupport
import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import spock.lang.Narrative
import spock.lang.Title

@Title("Search For Job Test")
@Narrative("""
As logged out customer
I want to be able to search for a job
""")
class SearchForJobTestSpec extends GebReportingSpec implements LogSupport {

    def cleanupSpec() {
        CachingDriverFactory.clearCache()
    }

    def "LoggedOutCustomerIsAbleToSearchForaJob"(){
        given: "Customer is on Akamai main page"
        to AkamaiPage

        when: "Customer specifies job title"
        searchForm.keyword = 'Test'

        and: "Customer specifies location"

        searchForm.joblocationdiv.click()
        searchForm.alllocations.click()

        and: "Custumer clicks 'Search'"
        SearchResultsPage searchResultsPage = searchForm.search()

        then: "Any job offers are found"
        assert { hasJobOffer(searchResultsPage.resultsList.jobTitle) }
        log.info("There is " + searchResultsPage.resultsList.jobTitle.size() + " job offers available.")
        log.info("First job offer is: " + searchResultsPage.resultsList.firstJobOffer())
    }

    def "CustomerIsNotifiedWhenNoOffersMatchGivenCriteria"(){

        given: "Customer is on Akamai main page"
        to AkamaiPage

        when: "Customer specifies job title"
        searchForm.keyword = 'XXX'

        and: "Custumer clicks 'Search'"
        SearchResultsPage searchResultsPage = searchForm.search()

        then: "Notification about no offers found is displayed"
        waitFor { assertNotification(searchResultsPage.resultsList.notification()) }
    }

    private static boolean  hasJobOffer(ArrayList jobTitles) {
        ( jobTitles.size() > 0 )
    }

    private static boolean assertNotification(String notificationText) {
        ( notificationText.contains("did not return any job results"))
    }
}
