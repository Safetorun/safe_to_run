package com.andro.secure

import com.airbnb.epoxy.Typed2EpoxyController
import io.github.dllewellyn.safetorun.reporting.GroupedSafeToRunReports

class ResultEpoxyController : Typed2EpoxyController<GroupedSafeToRunReports, Map<String, String>>() {

    var reports: GroupedSafeToRunReports? = null

    override fun buildModels(data: GroupedSafeToRunReports, result: Map<String, String>) {

        reports = data

        var count = 1

        data.failedReports.forEach {
            safeToRunFailedReport {
                report(it)
                id(count)
            }
            count++
        }

        data.successReports.forEach {
            safeToRunSuccessReport {
                report(it)
                id(count)
                count++
            }
        }

        data.warningReports.forEach {
            safeToRunWarnReport {
                report(it)
                id(count)
                count++
            }
        }

        result.forEach {
            simpleListItem {
                title(it.key)
                value(it.value)
                id(it.key)
            }
        }
    }
}
