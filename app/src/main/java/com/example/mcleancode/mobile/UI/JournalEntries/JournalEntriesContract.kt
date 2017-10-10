package com.example.mcleancode.mobile.UI.JournalEntries

import com.example.mcleancode.mobile.UI.Interfaces.IPresenter
import com.example.mcleancode.mobile.UI.Interfaces.IView

class JournalEntriesContract {
    interface View: IView<Presenter> {
        fun populateLocationsList(locations: ArrayList<JournalEntriesListViewModel>)
    }

    interface Presenter: IPresenter {
        fun openJournalEntries()
    }
}