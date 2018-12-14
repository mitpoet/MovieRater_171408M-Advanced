package com.example.tim.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ContextMenu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_view_movie_details.*
import kotlinx.android.synthetic.main.activity_save.*
import kotlinx.android.synthetic.main.activity_landing.*
import com.example.tim.movierater.MainActivity.Companion.movieList
import com.example.tim.movierater.MainActivity.Companion.nowShowing


class SaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        etLanguage_edit.setVisibility(View.GONE)
        etChkbox2_edit.setVisibility(View.INVISIBLE)
        etChkbox3_edit.setVisibility(View.INVISIBLE)
        etGone1_edit.setVisibility(View.GONE)
        etGone2_edit.setVisibility(View.GONE)
        etGone3_edit.setVisibility(View.GONE)

        etName_edit.setText(movieList[nowShowing].name)
        etDescription_edit.setText(movieList[nowShowing].description)
        etDate_edit.setText(movieList[nowShowing].date)

        when (movieList[nowShowing].language){
            "English" -> {
                rbtnButton1_edit.setChecked(true)
            }
            "Chinese" -> {
                rbtnButton2_edit.setChecked(true)
            }
            "Malay" -> {
                rbtnButton3_edit.setChecked(true)
            }
            "Tamil" -> {
                rbtnButton4_edit.setChecked(true)
            }
        }
        if (movieList[nowShowing].age){
            etChkbox1_edit.setChecked(true)
        }
        if (movieList[nowShowing].violence){
            etChkbox2_edit.setChecked(true)
            etChkbox2_edit.setVisibility(View.VISIBLE)

        }
        if (movieList[nowShowing].languageUsed){
            etChkbox3_edit.setChecked(true)
            etChkbox3_edit.setVisibility(View.VISIBLE)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.mSave) {
            var count = 0

            if (etName_edit.getText().toString().trim().isEmpty()) {
                etName_edit.setError("Field empty")
                count += 1}
            if (etDescription_edit.getText().toString().trim().isEmpty()) {
                etDescription_edit.setError("Field empty")
                count += 1}
            if (etDate_edit.getText().toString().trim().isEmpty()) {
                etDate_edit.setError("Field empty")
                count += 1}

            if(count == 0){
            movieList[nowShowing].name = etName_edit.text.toString()
            movieList[nowShowing].description = etDescription_edit.text.toString()
            movieList[nowShowing].language = etLanguage_edit.text.toString()
            movieList[nowShowing].date = etDate_edit.text.toString()
            movieList[nowShowing].age = etChkbox1_edit.isChecked
            movieList[nowShowing].violence = etChkbox2_edit.isChecked
            movieList[nowShowing].languageUsed = etChkbox3_edit.isChecked

            var viewMovieIntent = Intent(this,ViewMovieDetails::class.java)
            startActivity(viewMovieIntent)}
            }

        if (item?.itemId == R.id.mCancel) {

            var cancelIntent = Intent(this,LandingActivity::class.java)
            startActivity(cancelIntent)
        }
        return super.onOptionsItemSelected(item)
    }







}

