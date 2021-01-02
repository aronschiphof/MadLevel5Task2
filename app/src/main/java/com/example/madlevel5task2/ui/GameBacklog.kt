package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.adapter.GameAdapter
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel
import kotlinx.android.synthetic.main.fragment_game_backlog.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameBacklog : Fragment() {
    private lateinit var gameAdapter: GameAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewModel: GameViewModel by viewModels()
    private var games: ArrayList<Game> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAddGameResult()
        initRv()
    }

    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this@GameBacklog.games.clear()
            this@GameBacklog.games.addAll(games)

            /**
             * Sorting by date
             */
            this@GameBacklog.games.sortBy {
                it.releaseDate
            }
            gameAdapter.notifyDataSetChanged()

        })
    }

    private fun initRv() {

        gameAdapter = GameAdapter(games)
        viewManager = LinearLayoutManager(activity)

        createItemTouchHelper().attachToRecyclerView(rvGameBacklog)

        rvGameBacklog.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gameAdapter
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                viewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}