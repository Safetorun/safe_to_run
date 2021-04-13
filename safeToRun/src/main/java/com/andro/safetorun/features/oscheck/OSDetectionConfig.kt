package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.Conditional


data class OSDetectionConfig(val bannedOsResult: List<Conditional>)