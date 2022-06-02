package com.example.starvingnotebook

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.example.starvingnotebook.databinding.FragmentStatisticsBinding
import com.example.starvingnotebook.model.Day
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.util.*


internal class LineChartXAxisValueFormatter : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {

        // Convert float value to date string
        // Convert from seconds back to milliseconds to format time  to show to the user
        val emissionsMilliSince1970Time = value.toLong() * 1000

        // Show time in local version
        val timeMilliseconds = Date(emissionsMilliSince1970Time)
        val sdf = SimpleDateFormat("yy-MM-dd")
        return sdf.format(timeMilliseconds)
    }
}

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding
    private lateinit var viewModel: AppViewModel
    private lateinit var lastDay: Day
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(AppViewModel::class.java)

        viewModel.allDays.observe(viewLifecycleOwner, { setEvents(it) })
        viewModel.lastDay.observe(viewLifecycleOwner, {lastDay = it})

        val dtNow = Calendar.getInstance()
        dtNow.set(2022, 5, 20)

        val chartData = mutableListOf<Entry>(
            Entry(1630095570f, 61.2f),
            Entry(1631095570f, 61.2f),
            Entry(1632095570f, 61.2f),
            Entry(1633095570f, 61.2f),
            Entry(1634095570f, 61.2f),
            Entry(1635095570f, 61.2f),
            Entry(1636095570f, 61.2f),
            Entry(1637095570f, 61.2f),
            Entry(1638095570f, 61.2f),
            Entry(1639095570f, 71.2f),
            Entry(1640095570f, 61.2f),
            Entry(1641095570f, 81.2f),
            Entry(1642095570f, 61.2f),
            Entry(1643095570f, 91.2f),
            Entry(1644095570f, 61.2f),
            Entry(1645095570f, 41.2f),
            Entry(1646095570f, 61.2f),
            Entry(1647095570f, 61.2f),
            Entry(1648095570f, 61.2f),
            Entry(1649095570f, 61.2f),
            Entry(1650095570f, 61.2f),
            Entry(1651095570f, 61.2f),
            Entry(1652095570f, 61.2f),
            Entry(1653095570f, 61.2f),
            Entry(1654095570f, 61.2f),
        )
        val dataset = LineDataSet(chartData, "Weight")
        dataset.setColor(Color.parseColor("#ffda7f"))
        dataset.setDrawFilled(true)
        dataset.setDrawCircles(false)
        dataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        binding.lineChart.data = LineData(dataset)
        binding.lineChart.xAxis.valueFormatter = LineChartXAxisValueFormatter()
        binding.lineChart.invalidate()

        binding.calendarView.setOnDayClickListener { changeDone(it) }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun changeDone(eventDay: EventDay) {
        Toast.makeText(requireContext(), "Not yet implemented", Toast.LENGTH_SHORT).show()
    }

    private fun setEvents(days: List<Day>) {
        val currentWeekOfYear = getWeekOfYear()

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val data = mutableListOf<EventDay>()

        for (day in days) {
            val today = Calendar.getInstance()
            val dayCalendar = Calendar.getInstance()
            dayCalendar.time = sdf.parse(day.date)

            val icon = if (currentWeekOfYear < getWeekOfYear(dayCalendar)) R.drawable.ic_warning_green
                else if (today < dayCalendar) R.drawable.ic_warning_orange
                else if (day.isDone) R.drawable.ic_thumb_up
                else R.drawable.ic_thumb_down
            val color = if (day.isDone) Color.parseColor("#37885d") else Color.parseColor("#FF0000")
            data.add(EventDay(dayCalendar, icon))
        }

        binding.calendarView.setEvents(data)
    }

    private fun getWeekOfYear(date: Calendar = Calendar.getInstance()): Int {
        val year = date.get(Calendar.YEAR)
        return year * 100 + date.get(Calendar.WEEK_OF_YEAR)
    }

    private fun getCalendar(date: String): Calendar {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.time = sdf.parse(date)
        return calendar
    }

    private fun generateNextstarvingCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + 1)
        calendar.set(Calendar.DAY_OF_WEEK, (1..7).random())
        return calendar
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.statistics_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.generateStarvingDay -> generateStarvingDay()
        }
        return true
    }

    private fun generateStarvingDay() {
        if (lastDay == null) {
            Toast.makeText(requireContext(), "no last day, start to generate", Toast.LENGTH_SHORT).show()
            return
        }
        val calendar = getCalendar(lastDay.date)

        if (getWeekOfYear(calendar) <= getWeekOfYear()) {
            val nextStarvingDay = generateNextstarvingCalendar()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = Date(nextStarvingDay.timeInMillis)
            viewModel.addDay(Day(getWeekOfYear(nextStarvingDay), sdf.format(date), false))
            Toast.makeText(requireContext(), "ok " + lastDay._id, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "you have got a next starving day", Toast.LENGTH_SHORT).show()
        }

    }

}