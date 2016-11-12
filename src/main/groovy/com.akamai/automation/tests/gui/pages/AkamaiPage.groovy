package com.akamai.automation.tests.gui.pages

import com.akamai.automation.tests.gui.utils.LogSupport
import geb.Module
import geb.Page

class AkamaiPage extends Page implements LogSupport {
    static url = "http://akamaijobs.referrals.selectminds.com"
    static at = {
        title == "Akamai Careers - Jobs"
        searchForm.isDisplayed()
    }

    static content = {
        searchForm (required: true) { module SearchForm }
    }
}

class SearchForm extends Module implements LogSupport {
    static content = {
        alllocations (required: true) { $("li", id: "location_facet_chzn_o_14") }
        keyword (required: true) { $("input", name: "keyword", 0) }
        joblocationdiv (required: true) { $("div#loc_placeholder.jLocPlaceholder") }
        searchButton (to: SearchResultsPage, required: true){ $("span", text: "Search") }
    }

    SearchResultsPage search() {
        log.info("Showing job search results ...")
        searchButton.click()
        (SearchResultsPage) browser.page
    }
}
