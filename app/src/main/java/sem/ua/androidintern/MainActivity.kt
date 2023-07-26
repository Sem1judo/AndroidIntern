package sem.ua.androidintern


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sem.ua.androidintern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter", 0)
        }
        updateCounterText()
        binding.counterBtn.setOnClickListener {
            counter++
            updateCounterText()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt("counter", 0)
        updateCounterText()
    }

    private fun updateCounterText() {
        val counterText = resources.getString(R.string.counter, counter)
        binding.counterTv.text = counterText
    }
}
