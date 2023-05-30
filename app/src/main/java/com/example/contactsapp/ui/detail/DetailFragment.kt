package com.example.contactsapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentDetailBinding
import com.example.contactsapp.ui.detail.DetailFragmentDirections.actionDetailFragmentToNavHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private  val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.detailFragment = this
        //binding.detailToolbarTitle = "Kişi Detay"

        val bundle: DetailFragmentArgs by navArgs()
        val getPerson = bundle.person

        binding.personObject = getPerson

        viewModel.navigateToHome.observe(viewLifecycleOwner, { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToNavHome())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

    fun btnupdate(person_id: Int, person_name: String, person_number: String, person_group: String) {
        viewModel.updatePerson(person_id, person_name, person_number, person_group)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}