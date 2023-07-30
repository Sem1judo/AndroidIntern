package sem.ua.androidintern.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import sem.ua.androidintern.MainActivity
import sem.ua.androidintern.adapter.FavoriteAdapter
import sem.ua.androidintern.databinding.FragmentFavoritesBinding
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        sharedViewModel = (requireActivity() as MainActivity).getSharedViewModel()
        sharedViewModel.getFavoriteList().observe(viewLifecycleOwner) { favorites ->
            adapter.updateFavorites(favorites)
        }
    }
    private fun setupRecyclerView() {
        adapter = FavoriteAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }
}
