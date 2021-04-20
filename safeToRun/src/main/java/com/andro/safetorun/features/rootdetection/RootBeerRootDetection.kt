package com.andro.safetorun.features.rootdetection

import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport

class RootBeerRootDetection(
    private val rootDetectionConfig: RootDetectionConfig,
    private val rootDetectionChecker: RootDetectionChecker,
    private val rootDetectionStrings : RootDetectionStrings
) :
    SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        if (rootDetectionConfig.tolerateRoot) return SafeToRunReport.SafeToRunReportSuccess(
            rootDetectionStrings.rootDetectionDidNotRun()
        )

        return if (rootDetectionConfig.tolerateBusyBox) {
            rootDetectionChecker.isRootedWithBusyBoxCheck().not()
        } else {
            rootDetectionChecker.isRooted().not()
        }.let {
            if (it) {
                SafeToRunReport.SafeToRunReportSuccess(rootDetectionStrings.rootDetectionPassedMessage())
            } else {
                SafeToRunReport.SafeToRunReportFailure(
                    ROOT_DETECTION_ERROR_CODE,
                    rootDetectionStrings.rootDetectionFailedMessage()
                )
            }
        }
    }

    companion object {
        const val ROOT_DETECTION_ERROR_CODE = "rd"
    }
}
