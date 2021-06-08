package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.emulators.banAvdEmulator
import io.github.dllewellyn.safetorun.features.oscheck.emulators.banBluestacksEmulator
import io.github.dllewellyn.safetorun.features.oscheck.emulators.banGenymotionEmulator

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
