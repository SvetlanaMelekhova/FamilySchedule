package by.htp.first.myapplication.presenter.implement

import by.htp.first.myapplication.model.repository.DatabaseRepository
import by.htp.first.myapplication.model.repository.impl.DatabaseRepositoryImpl
import by.htp.first.myapplication.presenter.FragmentAllScheduleListPresenter
import by.htp.first.myapplication.view.fragment.FragmentAllScheduleListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FragmentAllScheduleListPresenterImpl
    (private var view: FragmentAllScheduleListView?): FragmentAllScheduleListPresenter {

    private val repository: DatabaseRepository = DatabaseRepositoryImpl()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun fetchData() {
        scope.launch {
           val list = repository.getPersonScheduleList()
            view?.showData(list)
        }
    }

    override fun close() {
       scope.cancel()
       view = null
    }
}