package com.example.contactsapp.ui.school

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.databinding.FragmentSchoolBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolFragment : Fragment() {

    private var _binding: FragmentSchoolBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val schoolViewModel =
            ViewModelProvider(this).get(SchoolViewModel::class.java)

        _binding = FragmentSchoolBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSchool
        schoolViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}