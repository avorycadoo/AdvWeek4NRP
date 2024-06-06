package com.example.advweek4nrp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4nrp.R
import com.example.advweek4nrp.databinding.FragmentStudentListBinding
import com.example.advweek4nrp.databinding.StudentListItemBinding
import com.example.advweek4nrp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
        ButtonDetailClickListener, ButtonDetailUpdateListener
{
    class StudentViewHolder(var view: StudentListItemBinding)
        : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,
            R.layout.student_list_item, parent, false)

        return StudentViewHolder(view)
//        val binding = StudentListItemBinding.inflate(
//            LayoutInflater.from(parent.context), parent, false)
//        return StudentViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this
//        holder.view.updateListener = this
//        holder.binding.txtID.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//
//        holder.binding.btnDetail.setOnClickListener {
////            val student = studentList[position]  // Get the current student object
////            val studentId = student.id
//            val action = StudentListFragmentDirections.actionStudentDetailFragment(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl).into(holder.view.imgPhoto, object:
            Callback{
            override fun onSuccess() {
                holder.view.progressImage.visibility = View.INVISIBLE
                holder.view.imgPhoto.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso_error", e.toString())
            }
            })

    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetailFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

//    override fun onButtonDetailUpdate(newStudentList: ArrayList<Student>) {
//        updateStudentList(newStudentList)
//    }

    override fun onButtonDetailUpdate(v: View) {
        TODO("Not yet implemented")
    }


}
