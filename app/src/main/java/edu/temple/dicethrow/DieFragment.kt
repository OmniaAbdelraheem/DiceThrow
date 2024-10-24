package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {
    val ROLL_KEY = "roll_key"
    val DIESIDE = "sidenumber"

    lateinit var dieTextView: TextView
    var dieSides: Int = 100
    var rollValue: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, 100)
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
        savedInstanceState?.let {
            rollValue = it.getInt(ROLL_KEY, -1)
        }

        if (rollValue == -1) {
            throwDie()
        } else {
            dieTextView.text = rollValue.toString()
        }
    }
    fun throwDie() {
        rollValue = Random.nextInt(1, (dieSides) + 1)
        dieTextView.text = rollValue.toString()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ROLL_KEY, rollValue)
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
