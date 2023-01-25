package com.example.exam_practice_1.view

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.exam_practice_1.R
import com.example.exam_practice_1.databinding.StoreFragmentBinding
import com.example.exam_practice_1.logTag
import com.example.exam_practice_1.model.Produs
import com.example.exam_practice_1.model.ProdusAdd
import com.example.exam_practice_1.viewmodel.ExamViewModel
import com.tapadoo.alerter.Alerter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import kotlin.random.Random

class StoreFragment : Fragment(R.layout.store_fragment) {

    private val binding by viewBinding(StoreFragmentBinding::bind)
    private val storeViewModel: ExamViewModel by activityViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveBtn.setOnClickListener {
            val produs = getFieldValues()
            if (produs != null) {
                storeViewModel.addProdus(produs)
                storeViewModel.repoAddProduct(
                    Produs(
                        Random.nextInt(10),
                        produs.nume,
                        produs.descriere,
                        produs.pret,
                        produs.cantitate,
                        produs.status
                    )
                )
            } else {
                Alerter.create(
                    requireActivity()
                ).setTitle("Error").setText("Try Again")
                    .setBackgroundColorRes(R.color.orange).show()
            }
            findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToMainFragment())
        }
    }

    private fun getFieldValues(): ProdusAdd? {
        val nume = binding.numeTIET.text.toString()
        val descriere = binding.descriereTIET.text.toString()
        val pret = binding.pretTIET.text.toString()
        val cantitate = binding.cantitateTIET.text.toString()
        val checkedButtonId = binding.statusRG.checkedRadioButtonId

        if (nume.isBlank() || descriere.isBlank() || pret.isBlank() || cantitate.isBlank() || checkedButtonId == -1) {
            Alerter.create(
                requireActivity()
            ).setTitle("Error").setText("One or more fields are empty")
                .setBackgroundColorRes(R.color.orange).show()
        } else {
            val status: Boolean
            val radioButton = binding.root.findViewById<RadioButton>(checkedButtonId)
            status = radioButton.text == "True"
            val toAdd: ProdusAdd =
                ProdusAdd(nume, descriere, pret.toInt(), cantitate.toInt(), status)
            return toAdd
        }
        return null
    }
}