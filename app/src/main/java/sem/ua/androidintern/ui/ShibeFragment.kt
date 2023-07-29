package sem.ua.androidintern.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sem.ua.androidintern.MainActivity
import sem.ua.androidintern.adapter.ShibeAdapter
import sem.ua.androidintern.databinding.FragmentShibeBinding
import sem.ua.androidintern.retrofit.ShibeService

class ShibeFragment : Fragment() {

    private lateinit var binding: FragmentShibeBinding
    private lateinit var adapter: ShibeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShibeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchData()
    }

    private fun setupRecyclerView() {
        adapter = ShibeAdapter(requireActivity() as MainActivity)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://shibe.online/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ShibeService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = service.getShibes(count = 100, urls = true, httpsUrls = true)
                if (response.isSuccessful) {
                    val shibes = response.body()
                    shibes?.let { shibeUrls ->
                        requireActivity().runOnUiThread {
                            adapter.submitList(shibeUrls)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}