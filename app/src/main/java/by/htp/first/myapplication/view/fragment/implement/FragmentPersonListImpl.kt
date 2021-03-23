package by.htp.first.myapplication.view.fragment.implement

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import by.htp.first.myapplication.R
import by.htp.first.myapplication.databinding.FragmentPersonListBinding
import by.htp.first.myapplication.function.setVisibileOrNot
import by.htp.first.myapplication.view.adapter.PersonAdapter
import by.htp.first.myapplication.model.entity.PersonData
import by.htp.first.myapplication.presenter.FragmentPersonListPresenter
import by.htp.first.myapplication.presenter.implement.FragmentPersonListPresenterImpl
import by.htp.first.myapplication.view.fragment.FragmentLoader
import by.htp.first.myapplication.view.fragment.FragmentPersonList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentPersonListImpl : Fragment(R.layout.fragment_person_list), FragmentPersonList {

    private val fragmentPersonListPresenter: FragmentPersonListPresenter =
        FragmentPersonListPresenterImpl(this)
    private lateinit var loader: FragmentLoader
    private lateinit var personAdapter: PersonAdapter
    private lateinit var binding: FragmentPersonListBinding
    private lateinit var fab: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader = requireActivity() as FragmentLoader
        binding = FragmentPersonListBinding.bind(view)
        fab = view.rootView.findViewById(R.id.fabAdd)
        personAdapter = PersonAdapter()
        binding.recyclerViewPersonList.apply {
            adapter = personAdapter
            layoutManager = GridLayoutManager(context as Context, 2)
            personAdapter.onEditIconClickListener = {
                loader.loadFragment(
                    FragmentEditPerson::class.java,
                    FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
                    bundleOf("personData" to it)
                )
            }
            personAdapter.onPersonScheduleInfoListListClickListener = {
                loader.loadFragment(
                    FragmentScheduleListImpl::class.java,
                    FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
                    bundleOf("personData" to it)
                )
            }
            fragmentPersonListPresenter.fetchData()
        }

        fab.setOnClickListener {
            loader.loadFragment(FragmentAddPerson(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun showData(list: List<PersonData>) {
        personAdapter.updateList(list)
        binding.tvNoPersonAdded.setVisibileOrNot(personAdapter.itemCount != 0)
    }
}


