package com.andro.secure.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andro.secure.BuildConfig
import com.andro.secure.ProtectedActivity
import com.andro.secure.databinding.ReportFragmentBinding
import com.auth0.jwt.interfaces.DecodedJWT
import com.safetorun.features.blacklistedapps.blacklistedAppCheck
import com.safetorun.features.debug.isDebuggableCheck
import com.safetorun.features.installorigin.installOriginCheckWithDefaultsCheck
import com.safetorun.features.oscheck.bannedBoardCheck
import com.safetorun.features.oscheck.bannedHardwareCheck
import com.safetorun.features.oscheck.emulator.banAvdEmulatorCheck
import com.safetorun.features.oscheck.emulator.banBluestacksEmulatorCheck
import com.safetorun.features.oscheck.emulator.banGenymotionEmulatorCheck
import com.safetorun.features.oscheck.safeToRunCombinedCheck
import com.safetorun.features.rootdetection.rootDetectionCheck
import com.safetorun.features.signatureverify.verifySignatureCheck
import com.safetorun.inline.safeToRunWithLogger
import com.safetorun.logger.loggerForCheck
import java.util.Date

class ReportFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        ReportFragmentBinding.inflate(inflater, container, false)
            .apply { setup(this) }
            .root

    private fun setup(binding: ReportFragmentBinding) {
        binding.runSensitiveAction.setOnClickListener {
            requireContext().canIRun("ReportFragmentCheck") {
                throw RuntimeException("Def")
            }

            Toast.makeText(it.context, "Performed sensitive action!!", Toast.LENGTH_LONG)
                .show()
        }

        binding.startProtectedActivity.setOnClickListener {
            startActivity(Intent(requireContext(), ProtectedActivity::class.java))
        }

        requireContext().canIRun("ReportFragmentCheck") { Log.v("Failure", "Failure") }

    }

    private fun DecodedJWT.verifierResult(): Map<String, String> =
        mapOf(
            "correctIssuer" to (issuer == BuildConfig.API_KEY),
            "anyFailures" to ((claims[Errors]?.asInt() ?: 1) > 0),
            "expired" to expiresAt.after(Date()),
            "anyWarnings" to ((claims[Warnings]?.asInt() ?: 1) > 0),
            "anyPasses" to ((claims[Passes]?.asInt() ?: 1) > 0)
        ).mapValues { it.value.toString() }


    private inline fun Context.canIRun(checkName: String, actionOnFailure: () -> Unit) {
        if (safeToRunWithLogger(
                logger = loggerForCheck(checkName),
                { verifySignatureCheck() },
                { banAvdEmulatorCheck() },
                { banGenymotionEmulatorCheck() },
                { banBluestacksEmulatorCheck() },
                { blacklistedAppCheck("Test app", "Test app 2") },
                { rootDetectionCheck() },
                { isDebuggableCheck() },
                {
                    safeToRunCombinedCheck(
                        listOf(
                            { bannedHardwareCheck("hardware") },
                            { bannedBoardCheck("board") }
                        )
                    )
                },
                {
                    safeToRunCombinedCheck(
                        listOf { installOriginCheckWithDefaultsCheck() },
                        listOf { !BuildConfig.DEBUG }
                    )
                },
                { verifySignatureCheck("Abc") }
            )()
        ) {
            actionOnFailure()
        }
    }

    companion object {
        const val MIN_OS_VERSION = 41
        const val Warnings = "warnings"
        const val Errors = "errors"
        const val Passes = "passes"
    }
}
