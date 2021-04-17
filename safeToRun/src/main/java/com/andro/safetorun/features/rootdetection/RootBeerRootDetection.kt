package com.andro.safetorun.features.rootdetection

import android.content.Context
import com.andro.safetorun.R
import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport
import com.scottyab.rootbeer.RootBeer

class RootBeerRootDetection(private val rootDetectionConfig: RootDetectionConfig) :
    SafeToRunCheck {

    override fun canRun(context: Context): SafeToRunReport {
        if (rootDetectionConfig.tolerateRoot) return SafeToRunReport.SafeToRunReportSuccess(
            context.resources.getString(R.string.root_detection_did_not_run)
        )

        return if (rootDetectionConfig.tolerateBusyBox) {
            RootBeer(context).isRootedWithBusyBoxCheck.not()
        } else {
            RootBeer(context).isRooted.not()
        }.let {
            if (it) {
                SafeToRunReport.SafeToRunReportSuccess(context.resources.getString(R.string.root_detection_passed))
            } else {
                SafeToRunReport.SafeToRunReportFailure(
                    ROOT_DETECTION_ERROR_CODE,
                    context.resources.getString(R.string.root_detection_failed)
                )
            }
        }
    }

    companion object {
        const val ROOT_DETECTION_ERROR_CODE = "rd"
    }
}
