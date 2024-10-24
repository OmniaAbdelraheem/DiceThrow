package edu.temple.dicethrow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {

    private var dieFragment: DieFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.dieContainer)
                    !is DieFragment) {
            dieFragment = DieFragment.newInstance(100)
            supportFragmentManager.beginTransaction()
                .replace(R.id.dieContainer, dieFragment!!)
                .commit()
        }
        if (supportFragmentManager.findFragmentById(R.id.buttonContainer)
                    !is ButtonFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.buttonContainer, ButtonFragment())
                .commit()
        }
    }
    override fun ButtonClicked() {
        dieFragment?.throwDie()
    }
}
