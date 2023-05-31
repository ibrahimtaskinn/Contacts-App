package com.example.contactsapp.ui.social

import android.os.Bundle
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
import com.example.contactsapp.databinding.FragmentSocialBinding
import com.example.contactsapp.ui.adapter.PersonsAdapter
import com.example.contactsapp.util.skip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocialFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentSocialBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SocialViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_social, container, false)
        binding.socialFragment = this

        viewModel.repository.personsByGroup.observe(viewLifecycleOwner){
            val adapter = PersonsAdapter(requireContext(), it, viewModel)
            binding.personsAdapter = adapter
        }


        viewModel.navigateToDetailsEvent.observe(viewLifecycleOwner, { person ->
            person?.let {
                val nav = SocialFragmentDirections.actionNavSocialToDetailFragment(it)
                findNavController().navigate(nav)
                viewModel.resetNavigateToDetailsEvent()
            }
        })

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@SocialFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    fun fabClick(it: View) {
        Navigation.skip(it, R.id.action_nav_social_to_addPersonFragment)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}