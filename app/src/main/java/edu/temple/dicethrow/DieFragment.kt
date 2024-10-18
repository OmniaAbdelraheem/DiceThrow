package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.channels.BroadcastChannel
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"

    lateinit var dieTextView: TextView

    var dieSides: Int = 100

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        throwDie()
        view.setOnClickListener {
            throwDie()
        }
    }

    fun throwDie() {
        val rollResult = Random.nextInt(1, (dieSides) + 1).toString()
        dieTextView.text = rollResult
    }


    companion object {
        fun newInstance(sides: Int) : DieFragment {
        val fragment = DieFragment()
        val bundle = Bundle()
        bundle.putInt("sidenumber", sides)
        fragment.arguments = bundle
        return fragment
    }
}
}