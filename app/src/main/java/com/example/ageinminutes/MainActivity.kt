package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{ view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view : View) {
        var today : Calendar = Calendar.getInstance() ;
        var year = today.get(Calendar.YEAR)
        var month = today.get(Calendar.MONTH)
        var day = today.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this  ,
            DatePickerDialog.OnDateSetListener {
                    view, year, month, dayOfMonth ->
                DateChange(year , month , dayOfMonth)

            } , year , month ,day
            ).show()
    }

    fun DateChange(year:Int , month:Int  , dayOfMonth:Int ) {
        var birth : Calendar = Calendar.getInstance() ;
        birth.set(year , month ,dayOfMonth)

        var formatter = SimpleDateFormat("dd-MM-yyyy")

        tvBirthDay.text = formatter.format(birth.time)

        var today : Calendar = Calendar.getInstance() ;

        tvCurrentDate.text = formatter.format(today.time)

        var this_year = today.get(Calendar.YEAR) ;

        var this_year_days = Calendar.getInstance()

        this_year_days.set(this_year , 11 ,31)

        var age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)

        var nbrDays = 0

        if(today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--
            nbrDays = birth.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) -1
        } else {
            nbrDays = this_year_days.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) + birth.get(Calendar.DAY_OF_YEAR) -1
        }

        tvAge.text = ""+age+" years old"
        tvDaysLeft.text = ""+nbrDays+" days"
        infoLayout.visibility = View.VISIBLE

    }
}