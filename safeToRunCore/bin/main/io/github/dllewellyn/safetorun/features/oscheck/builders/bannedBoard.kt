package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned board to the list
 *
 * @param bannedBoard the model to ban
 */
fun OSInformationQuery.bannedBoard(bannedBoard: String): Conditional =
    baseOsCheck({ "Banned board ${board()} == $bannedBoard" }) {
        board() == bannedBoard
    }
