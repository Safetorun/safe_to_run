package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck

/**
 * Add a banned board to the list
 *
 * @param bannedBoard the model to ban
 */
fun OSInformationQuery.bannedBoard(bannedBoard: String): Conditional =
    baseOsCheck({ "Banned board ${board()} == $bannedBoard" }) {
        board() == bannedBoard
    }
