package com.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.emulators.banAvdEmulator
import com.safetorun.features.oscheck.emulators.banBluestacksEmulator
import com.safetorun.features.oscheck.emulators.banGenymotionEmulator

/**
 * Ban the default AVD emulator from running
 */
fun banAvdEmulator(): Conditional =
    OSConfiguration.banAvdEmulator()

/**
 * Ban the bluestacks emulator from running
 */
fun banBluestacksEmulator(): Conditional =
    OSConfiguration.banBluestacksEmulator()

/**
 * Ban the genymotion emulator
 */
fun banGenymotionEmulator(): Conditional =
    OSConfiguration.banGenymotionEmulator()
