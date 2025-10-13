package com.kfccorp.kfcorder

import android.os.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.google.firebase.Firebase
import com.google.firebase.database.*
import com.kfccorp.kfcorder.databinding.ActivityMainBinding
import com.kfccorp.kfcorder.models.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var inventory: ArrayList<Product>
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: TimeRunnable
    private val callBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {}                                                           // ABSORB BACK NAVIGATION ACTION
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callBack)
        ScreenSize.getScreenSize(this)

        database = Firebase.database
        dbRef = database.reference
        inventory = arrayListOf()

        populateCategories()
        fetchInventory()

        runnable = TimeRunnable(handler, Global.EXPIRE)
        handler.postDelayed(runnable, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun fetchInventory() {
        dbRef.child("temp_Inventory").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                inventory.clear()
                if (snapshot.exists()) for (item in snapshot.children)
                    inventory.add(item.getValue(Product::class.java)!!)
                populateCat1Products()
                populateCat2Products()
                populateCat3Products()
                populateCat4Products()
                populateCat5Products()
                populateCat6Products()
                populateCat7Products()
                populateCat8Products()
                populateCat9Products()
                populateCat10Products()
                populateCat11Products()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun populateCat11Products() {
        cat11Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("DRINKS", true)
        })
        val img = arrayListOf(R.drawable.proscr, R.drawable.procf, R.drawable.proit,
            R.drawable.proscr, R.drawable.prorf, R.drawable.proscr, R.drawable.prosf)
        for (i in 0 until pro.size) {
            cat11Products.add(Cat11Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat10Products() {
        cat10Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("FIXINS & EXTRAS", true)
        })
        val img = arrayListOf(R.drawable.pronull, R.drawable.proer, R.drawable.pronull,
            R.drawable.pronull, R.drawable.pronull, R.drawable.pronull, R.drawable.pronull,
            R.drawable.proms)
        for (i in 0 until pro.size) {
            cat10Products.add(Cat10Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat9Products() {
        cat9Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("DESSERTS", true)
        })
        val img = arrayListOf(R.drawable.probn)
        for (i in 0 until pro.size) {
            cat9Products.add(Cat9Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat8Products() {
        cat8Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("SNACKS COMBOS", true)
        })
        val img = arrayListOf(R.drawable.pronull, R.drawable.pronull, R.drawable.pronull,
            R.drawable.pronull, R.drawable.pronull, R.drawable.prozr)
        for (i in 0 until pro.size) {
            cat8Products.add(Cat8Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat7Products() {
        cat7Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("ALA CARTE SNACKS", true)
        })
        val img = arrayListOf(R.drawable.prols, R.drawable.pronull, R.drawable.prors)
        for (i in 0 until pro.size) {
            cat7Products.add(Cat7Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat6Products() {
        cat6Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("BOWLS & PLATTERS", true)
        })
        val img = arrayListOf(R.drawable.probp1, R.drawable.probp2, R.drawable.proarb,
            R.drawable.prosrb)
        for (i in 0 until pro.size) {
            cat6Products.add(Cat6Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat5Products() {
        cat5Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("SIGNATURE INDIVIDUAL CHICKEN", true)
        })
        val img = arrayListOf(R.drawable.pro1cm, R.drawable.pro1cmm, R.drawable.pronull,
            R.drawable.pro2cm, R.drawable.pro2cmf)
        for (i in 0 until pro.size) {
            cat5Products.add(Cat5Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat4Products() {
        cat4Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("SIGNATURE ALA CARTE", true)
        })
        val img = arrayListOf(R.drawable.pro1ca, R.drawable.pro2ca)
        for (i in 0 until pro.size) {
            cat4Products.add(Cat4Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat3Products() {
        cat3Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("FULLY LOADED BOXES", true)
        })
        val img = arrayListOf(R.drawable.pro1fm, R.drawable.pro2fm)
        for (i in 0 until pro.size) {
            cat3Products.add(Cat3Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat2Products() {
        cat2Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("BUCKET MEALS", true)
        })
        val img = arrayListOf(R.drawable.pro6bm, R.drawable.pro8bm)
        for (i in 0 until pro.size) {
            cat2Products.add(Cat2Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCat1Products() {
        cat1Products.clear()
        val pro = arrayListOf<Product>()
        pro.addAll(inventory.filter { item ->
            item.category.equals("ALL CHICKEN BUCKET", true)
        })
        val img = arrayListOf(R.drawable.prob10, R.drawable.prob15, R.drawable.prob20,
            R.drawable.prob6)
        for (i in 0 until pro.size) {
            cat1Products.add(Cat1Products(pro[i].id!!, img[i],
                pro[i].product!!, pro[i].price!!, pro[i].quantity!!))
        }
    }

    private fun populateCategories() {
        val cat1 = Category(R.drawable.catabc, "ALL CHICKEN\nBUCKET")
        val cat2 = Category(R.drawable.catbm, "BUCKET MEALS")
        val cat3 = Category(R.drawable.catfl, "FULLY LOADED\nBOXES")
        val cat4 = Category(R.drawable.catsam, "SIGNATURE ALA\nCARTE RICE MEALS")
        val cat5 = Category(R.drawable.catsim, "SIGNATURE\nINDIVIDUAL CHICKEN\nMEALS")
        val cat6 = Category(R.drawable.catrb, "BOWLS & PLATTERS")
        val cat7 = Category(R.drawable.catacs, "ALA CARTE SNACKS")
        val cat8 = Category(R.drawable.catsc, "SNACK COMBOS")
        val cat9 = Category(R.drawable.catdst, "DESSERTS")
        val cat10 = Category(R.drawable.catfex, "FIXINS & EXTRAS")
        val cat11 = Category(R.drawable.catdrk, "DRINKS")
        categoryList.add(cat1)
        categoryList.add(cat2)
        categoryList.add(cat3)
        categoryList.add(cat4)
        categoryList.add(cat5)
        categoryList.add(cat6)
        categoryList.add(cat7)
        categoryList.add(cat8)
        categoryList.add(cat9)
        categoryList.add(cat10)
        categoryList.add(cat11)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}