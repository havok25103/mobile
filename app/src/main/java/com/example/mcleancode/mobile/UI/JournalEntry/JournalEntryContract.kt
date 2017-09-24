package com.example.mcleancode.mobile.UI.JournalEntry

import com.example.mcleancode.mobile.UI.Common.IPresenter
import com.example.mcleancode.mobile.UI.Common.IView

class JournalEntryContract {
    interface View: IView<Presenter> {
        fun showTitle(title: String)

        fun showText(text: String)
    }

    interface Presenter: IPresenter {
        fun openJournalEntry(locationId: String)
    }
}