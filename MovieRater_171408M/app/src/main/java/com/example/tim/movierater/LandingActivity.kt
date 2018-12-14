package com.example.tim.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tim.movierater.MainActivity.Companion.movieList
import com.example.tim.movierater.MainActivity.Companion.nowShowing
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_save.*
import javax.xml.parsers.SAXParser


class LandingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)



        for (i in MainActivity.movieList.indices){

            //Created a dynamic textview
            var layout = LinearLayout(this)
            findViewById<LinearLayout>(R.id.landingActivity).addView(layout)
            layout.orientation = LinearLayout.HORIZONTAL

            //Add image to the layout
            var image = ImageView(this)
            image.setImageResource(R.drawable.musd_assignment_1)
            layout.addView(image)

            var dynamicTv = TextView(this)
            dynamicTv.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            dynamicTv.setText(MainActivity.movieList[i].name)
            dynamicTv.setId(i)
            dynamicTv.setMinHeight(150)
            dynamicTv.gravity = Gravity.CENTER
            layout.addView(dynamicTv)
            dynamicTv.setClickable(true)



            registerForContextMenu(dynamicTv)


            dynamicTv.setOnClickListener(){
                nowShowing = i
                var clickableIntent = Intent(this, ViewMovieDetails::class.java)
                startActivity(clickableIntent)
            }
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.landing,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        for(i in movieList.indices){
        if(v?.id == i){
            menu?.add(1,1002+i,1,"Edit")
        }
    }
}

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var mainIntent = Intent(this, MainActivity::class.java)
        if (item?.itemId == R.id.mAddMenu) {
            startActivity(mainIntent)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onContextItemSelected(item: MenuItem?): Boolean {
        for (i in movieList.indices){
            if(item?.itemId == 1002+i){
                nowShowing = i;
                var saveIntent = Intent(this, SaveActivity::class.java)
                startActivity(saveIntent)
            }
        }
        return super.onContextItemSelected(item)
    }
}