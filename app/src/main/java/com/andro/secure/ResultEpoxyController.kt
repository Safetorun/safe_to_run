package com.andro.secure

import com.airbnb.epoxy.TypedEpoxyController
import io.github.dllewellyn.safetorun.reporting.GroupedSafeToRunReports

class ResultEpoxyController : TypedEpoxyController<GroupedSafeToRunReports>() {

    override fun buildModels(data: GroupedSafeToRunReports) {

        var count = 1

        data.failedReports.forEach {
            SafeToRunFailedReportBindingModel_()
                .report(it)
                .id(count)
                .addTo(this)
            count++
        }

        data.successReports.forEach {
            SafeToRunSuccessReportBindingModel_()
                .report(it)
                .id(count)
                .addTo(this)
            count++
        }

        data.warningReports.forEach {
            SafeToRunWarnReportBindingModel_()
                .report(it)
                .id(count)
                .addTo(this)
            count++
        }
    }
}