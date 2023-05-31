package com.example.contactsapp.ui.family

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentFamilyBinding
import com.example.contactsapp.ui.adapter.PersonsAdapter
import com.example.contactsapp.util.skip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FamilyFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentFamilyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FamilyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_family, container, false)
        binding.familyFragment = this

        viewModel.repository.personsByGroup.observe(viewLifecycleOwner){
            val adapter = PersonsAdapter(requireContext(), it, viewModel)
            binding.personsAdapter = adapter
        }

        viewModel.navigateToDetailsEvent.observe(viewLifecycleOwner, { person ->
            person?.let {
                val nav = FamilyFragmentDirections.actionNavFamilyToDetailFragment(it)
                findNavController().navigate(nav)
                viewModel.resetNavigateToDetailsEvent()
            }
        })

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@FamilyFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    fun fabClick(it: View) {
        Navigation.skip(it, R.id.action_nav_family_to_addPersonFragment)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.searchPerson(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.searchPerson(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPersons()
        Log.e("Kişi Anasayfa", "Dönüldü")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
