package by.htp.first.myapplication.presenter

import by.htp.first.myapplication.model.entity.PersonScheduleData

interface FragmentAddSchedulePresenter {

    fun addData(personScheduleData: PersonScheduleData)
    fun close()
}