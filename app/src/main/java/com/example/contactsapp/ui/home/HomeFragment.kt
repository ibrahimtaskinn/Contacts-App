package com.example.contactsapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentHomeBinding
import com.example.contactsapp.ui.adapter.PersonsAdapter
import com.example.contactsapp.util.skip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.homeFragment = this

        //binding.homeToolbarTitle = "Kişiler"
        //(activity as AppCompatActivity).setSupportActionBar(binding.Hometoolbar)

        viewModel.personsList.observe(viewLifecycleOwner){
            val adapter = PersonsAdapter(requireContext(), it, viewModel)
            binding.personsAdapter = adapter
        }

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

   /** override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.FloatActionButton.setOnClickListener(fabClick())
    }*/

    fun fabClick(it: View) {
        Navigation.skip(it, R.id.action_homeFragment_to_addPersonFragment)
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
        viewModel.loadperson()
        Log.e("Kişi Anasayfa", "Dönüldü")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}