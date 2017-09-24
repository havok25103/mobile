package com.example.mcleancode.mobile.Finders.Interfaces

import android.content.ContentValues

interface IFinder {
    fun findAll(): ArrayList<*>

    fun findById(id: String): Any

    fun update(id: String, values: ContentValues)
}