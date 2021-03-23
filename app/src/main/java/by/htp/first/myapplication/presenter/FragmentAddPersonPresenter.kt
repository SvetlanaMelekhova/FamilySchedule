package by.htp.first.myapplication.presenter

import by.htp.first.myapplication.model.entity.PersonData

interface FragmentAddPersonPresenter {

        fun addData(personData: PersonData)
        fun close()
}