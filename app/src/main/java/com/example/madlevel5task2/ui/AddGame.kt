package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.model.GameViewModel
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGame : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveGamesFab.setOnClickListener {
            onAddGame()
            findNavController().popBackStack()
        }
    }

    private fun formatDay(day: Int): String {
        if (day<= 9) return "0$day"
        else return day.toString()
    }

    private fun formatMonth(month: Int): String {
        if (month <= 9) return "0$month"
        else return month.toString()
    }

    private fun onAddGame() {
        val gameTitle = etTitle.text.toString()
        val gamePlatform = etPlatform.text.toString()
        val day = etDay.text.toString().toInt()
        val month = etMonth.text.toString().toInt()
        val year = etYear.text.toString()

        val formatter = DateTimeFormatter.ofPattern("yyyy MM dd")
        val parsedDate = LocalDate.parse("$year ${formatMonth(month)} ${formatDay(day)}", formatter)
        val result: ZonedDateTime = parsedDate.atStartOfDay(ZoneId.systemDefault())

        if (gameTitle.isNotBlank() && gamePlatform.isNotBlank()) {
            viewModel.insertGame(
                Game(
                    gameTitle, gamePlatform, Date.from(result.toInstant())
                )
            )
        } else {
            Toast.makeText(activity, R.string.not_valid_game, Toast.LENGTH_SHORT).show()
        }
    }
}