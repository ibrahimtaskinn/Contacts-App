package com.example.contactsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.data.entity.Persons
import com.example.contactsapp.databinding.CardItemBinding
import com.example.contactsapp.ui.home.HomeFragmentDirections
import com.example.contactsapp.ui.home.HomeViewModel
import com.example.contactsapp.util.skip
import com.google.android.material.snackbar.Snackbar

class PersonsAdapter(var mContext: Context,
                     var PersonList: List<Persons>, var viewModel: HomeViewModel
) :
    RecyclerView.Adapter<PersonsAdapter.CardDesignHolder>() {
    inner class CardDesignHolder(design: CardItemBinding) : RecyclerView.ViewHolder(design.root) {
        var design : CardItemBinding
        init {
            this.design = design
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design:CardItemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return PersonList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val person = PersonList.get(position)
        val design = holder.design

        design.personObject = person

        design.itemcard.setOnClickListener {
            val nav = HomeFragmentDirections.actionHomeFragmentToDetailFragment(person)
            Navigation.skip(it, nav)
        }

        design.deleteImg.setOnClickListener {
            Snackbar.make(it, "${person.person_name} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.deletePerson(person)
                }.show()
        }
    }
}