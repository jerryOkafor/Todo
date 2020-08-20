package me.jerryhanks.todo.createtaskfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.ct_feat_fragment_first.*
import me.jerryhanks.createtaskfeature.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ct_feat_fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(toolbar)

            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)

//            setHomeAsUpIndicator(
//                ContextCompat.getDrawable(
//                    this@NewTaskActivity,
//                    R.drawable.ic_close
//                )
//            )
            }
        }

        btnAddTag.setOnClickListener { findNavController().navigate(R.id.action_newTaskFragment_to_addTagFragment) }

    }
}