package com.akamai.automation.tests.gui.pages

import com.akamai.automation.tests.gui.utils.LogSupport
import geb.Module
import geb.Page

class SearchResultsPage extends Page implements LogSupport {
    static at = {
        title == "Akamai Careers - Jobs"
        waitFor (30, 0.1) { resultsList.isDisplayed() }
    }

    static content = {
        resultsList { module ResultsList }
    }
}

class ResultsList extends Module implements LogSupport {
    static content = {
        jobTitle (required: false) { $("a.job_link.font_bold")*.text() }
        noResultsBox (required: false) { $("div").find(".no_results_box")*.text() }
    }
    String "firstJobOffer"() {
        log.info("Retrieving first job title ...")
        return jobTitle[0]
    }

    String "notification"() {
        log.info("Retrieving notification text ...")
        return noResultsBox[0]
    }
}




