package com.example.contactsapp.ui.addperson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentAddPersonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPersonFragment : Fragment() {

    private var _binding: FragmentAddPersonBinding? = null
    private val binding get() = _binding!!
    private  val viewModel: AddPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_person, container, false)

        binding.addPersonFragment = this
        //binding.addPersonToolbarTitle = "Kişi Kayıt"

        viewModel.navigateToHome.observe(viewLifecycleOwner, { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(AddPersonFragmentDirections.actionAddPersonFragmentToNavHome())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

    fun btnsave(person_name: String, person_number: String, person_group: String) {
        viewModel.savePerson(person_name, person_number, person_group)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}