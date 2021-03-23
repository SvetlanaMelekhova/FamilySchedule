package by.htp.first.myapplication.view.fragment.implement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import by.htp.first.myapplication.R
import by.htp.first.myapplication.databinding.FragmentAllScheduleListBinding
import by.htp.first.myapplication.function.setVisibileOrNot
import by.htp.first.myapplication.view.adapter.PersonScheduleAdapter
import by.htp.first.myapplication.model.entity.PersonScheduleData
import by.htp.first.myapplication.presenter.FragmentAllScheduleListPresenter
import by.htp.first.myapplication.presenter.implement.FragmentAllScheduleListPresenterImpl
import by.htp.first.myapplication.view.fragment.FragmentAllScheduleListView
import by.htp.first.myapplication.view.fragment.FragmentLoader
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentAllScheduleListImpl : Fragment(R.layout.fragment_all_schedule_list),
    FragmentAllScheduleListView {

    private val presenter: FragmentAllScheduleListPresenter = FragmentAllScheduleListPresenterImpl(this)
    private lateinit var binding: FragmentAllScheduleListBinding
    private lateinit var loader: FragmentLoader
    private lateinit var personScheduleAdapter: PersonScheduleAdapter
    private lateinit var fab: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader = requireActivity() as FragmentLoader
        binding = FragmentAllScheduleListBinding.bind(view)
        fab = view.rootView.findViewById(R.id.fabAdd)
        personScheduleAdapter = PersonScheduleAdapter()

        binding.recyclerViewSchedule.apply {
            adapter = personScheduleAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            personScheduleAdapter.onScheduleInfoItemClickListener = {
            }
        }
        presenter.fetchData()

        fab.setOnClickListener {
            loader.loadFragment(FragmentPersonListImpl(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun showData(list: List<PersonScheduleData>) {
        personScheduleAdapter.updateLists(list)
        binding.tvNoScheduleAdded.setVisibileOrNot(list.isNotEmpty())
    }
}
