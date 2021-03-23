package by.htp.first.myapplication.presenter.implement

import by.htp.first.myapplication.model.repository.impl.DatabaseRepositoryImpl
import by.htp.first.myapplication.model.entity.PersonData
import by.htp.first.myapplication.model.repository.DatabaseRepository
import by.htp.first.myapplication.presenter.FragmentAddPersonPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FragmentAddPersonPresenterImpl: FragmentAddPersonPresenter {

    private val repository: DatabaseRepository = DatabaseRepositoryImpl()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun addData(personData: PersonData) {
        scope.launch { repository.addPerson(personData) }
    }

    override fun close() {
        scope.cancel()
    }

}