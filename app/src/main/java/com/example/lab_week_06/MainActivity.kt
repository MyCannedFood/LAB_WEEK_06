package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }
    private val catAdapter by lazy {
        //Glide is used here to load the images
        //Here we are passing the onClickListener function to the Adapter
        CatAdapter(layoutInflater, GlideImageLoader(this), object :
            CatAdapter.OnClickListener {
            //When this is triggered, the pop up dialog will be shown
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }
    // In MainActivity.kt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the adapter for the recycler view
        recyclerView.adapter = catAdapter
        // Setup the layout manager for the recycler view
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Add data to the model list in the adapter (Now with 10 items)
        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.ExoticShorthair,
                    "Garfield",
                    "Loves lasagna, hates Mondays",
                    "https://cdn2.thecatapi.com/images/5g2.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.BalineseJavanese,
                    "Luna",
                    "Elegant and graceful",
                    "https://cdn2.thecatapi.com/images/4m7.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.AmericanCurl,
                    "Simba",
                    "Aspiring king of the house",
                    "https://cdn2.thecatapi.com/images/a2h.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.BalineseJavanese,
                    "Bella",
                    "Loves sunbathing by the window",
                    "https://cdn2.thecatapi.com/images/2b5.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.ExoticShorthair,
                    "Oliver",
                    "Master of the 'sad eyes' look for treats",
                    "https://cdn2.thecatapi.com/images/bq6.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.AmericanCurl,
                    "Lucy",
                    "Has a PhD in knocking things off tables",
                    "https://cdn2.thecatapi.com/images/dD90g4JAC.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Max",
                    "Professional napper and part-time mischief-maker",
                    "https://cdn2.thecatapi.com/images/MTc5OTM2OA.jpg"
                )
            )
        )

        // --- FIX STARTS HERE ---

        // 1. Create an instance of SwipeToDeleteCallback and pass the adapter to it.
        val swipeHandler = CatAdapter.SwipeToDeleteCallback(catAdapter)

        // 2. Create an ItemTouchHelper with the swipe handler.
        val itemTouchHelper = ItemTouchHelper(swipeHandler)

        // 3. Attach the ItemTouchHelper to the RecyclerView.
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // --- FIX ENDS HERE ---
    }
    //This will create a pop up dialog when one of the items from the recycler view is clicked.
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            //Set the title for the dialog
            .setTitle("Cat Selected")
            //Set the message for the dialog
            .setMessage("You have selected cat ${cat.name}")
            //Set if the OK button should be enabled
            .setPositiveButton("OK") { _, _ -> }.show()
    }
}
