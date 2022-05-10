package com.safetorun.features.oscheck

import com.safetorun.conditional.Conditional

internal data class OSDetectionConfig(val bannedOsResult: List<Conditional>)
