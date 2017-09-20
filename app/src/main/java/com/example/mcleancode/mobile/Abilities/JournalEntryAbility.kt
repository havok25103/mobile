package com.example.mcleancode.mobile.Abilities

import com.example.mcleancode.mobile.Enums.LocationStatusEnum

class JournalEntryAbility {
    companion object {
        fun needsReading(status: Int): Boolean {
            return LocationStatusEnum.Found.status == status
        }

        fun canView(status: Int): Boolean {
            return LocationStatusEnum.Found.status == status ||
                    LocationStatusEnum.Read.status == status
        }
    }
}