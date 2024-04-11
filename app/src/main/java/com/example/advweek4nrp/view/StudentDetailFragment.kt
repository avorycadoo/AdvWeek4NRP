package com.example.advweek4nrp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4nrp.R
import com.example.advweek4nrp.databinding.FragmentStudentDetailBinding
import com.example.advweek4nrp.databinding.FragmentStudentListBinding
import com.example.advweek4nrp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Call fetch function
        detailViewModel.fetch()

        // Observer for the Student LiveData
        observeViewModel()
    }

    fun observeViewModel() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer{
            var student = it
            binding.apply {
                txtID.setText(student.id)
                txtName.setText(student.name)
                txtBod.setText(student.bod)
                txtPhone.setText(student.phone)
            }
            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(
                            student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_add_24
                        )
                    }
            }
        })
    }
}

