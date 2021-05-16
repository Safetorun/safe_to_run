package com.andro.secure

import com.airbnb.epoxy.TypedEpoxyController
import io.github.dllewellyn.safetorun.reporting.GroupedSafeToRunReports

class ResultEpoxyController : TypedEpoxyController<GroupedSafeToRunReports>() {

    override fun buildModels(data: GroupedSafeToRunReports) {

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
    }
}
