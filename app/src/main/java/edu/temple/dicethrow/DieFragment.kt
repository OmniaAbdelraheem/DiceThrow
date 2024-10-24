package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    private val dieViewModel: DieViewModel by lazy {
        ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }
    val ROLL_KEY = "roll_key"
    val DIESIDE = "sidenumber"

    lateinit var dieTextView: TextView
    var dieSides: Int = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, 6)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getDieRoll().observe(viewLifecycleOwner){

            dieTextView.text = it.toString()
        }
         if(dieViewModel.getDieRoll().value == null){

             throwDie()
         }
    }
    fun throwDie() {
        dieViewModel.setDieRoll(Random.nextInt(1, (dieSides) + 1))
    }

    companion object {
        fun newInstance(sides: Int): DieFragment {
            val fragment = DieFragment()
            val bundle = Bundle()
            bundle.putInt("sidenumber", sides)
            fragment.arguments = bundle
            return fragment
        }
    }
}
