package by.htp.first.myapplication.presenter

import by.htp.first.myapplication.model.entity.PersonData

interface FragmentEditPersonPresenter {

    fun updateData(personData: PersonData)
    fun close()
}