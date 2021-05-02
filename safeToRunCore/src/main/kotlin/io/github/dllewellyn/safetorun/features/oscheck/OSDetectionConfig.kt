package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional

data class OSDetectionConfig(val bannedOsResult: List<Conditional>)