package com.example.mcleancode.mobile.UI.JournalEntry

import com.example.mcleancode.mobile.UI.Interfaces.IPresenter
import com.example.mcleancode.mobile.UI.Interfaces.IView

class JournalEntryContract {
    interface View: IView<Presenter> {
        fun showTitle(title: String)

        fun showText(text: String)
    }

    interface Presenter: IPresenter {
        fun openJournalEntry(locationId: String)
    }
}