package com.kfccorp.kfcorder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.RadioGroup
import com.kfccorp.kfcorder.R
import com.kfccorp.kfcorder.databinding.DetailsBinding
import com.kfccorp.kfcorder.interfaces.ProductInterface
import com.kfccorp.kfcorder.models.*

@SuppressLint("SetTextI18n")
class Details : Fragment() {

    private lateinit var binding: DetailsBinding
    private var amount : Int = 1
    private var price : Int = 0
    private var categoryPos : Int = 0
    private var productPos : Int = 0
    private var varPrice : Int = 0
    private lateinit var itemList : List<ProductInterface>
    private lateinit var cart : CartListItem
    private val isEnabled = mutableListOf<Int>()
    private var chicken: String? = null
    private var regFix1: String? = null
    private var regFix2: String? = null
    private var regFix3: String? = null
    private var regFix4: String? = null
    private var larFix1: String? = null
    private var larFix2: String? = null
    private var larFix3: String? = null
    private var larFix4: String? = null
    private var drink: String? = null
    private var medDrink1: String? = null
    private var medDrink2: String? = null
    private var medDrink3: String? = null
    private var medDrink4: String? = null
    private var larDrink1: String? = null
    private var larDrink2: String? = null
    private var larDrink3: String? = null
    private var larDrink4: String? = null
    private var incMeal = 0
    private var incMealDrink = 0
    private var incLarFix1 = 0
    private var incLarFix2 = 0
    private var incLarFix3 = 0
    private var incLarFix4 = 0
    private var incRegFix1 = 0
    private var incRegFix2 = 0
    private var incRegFix3 = 0
    private var incRegFix4 = 0
    private var isSelChicken = false
    private var isSelDrink = false
    private var isSelLarDrink1 = false
    private var isSelLarDrink2 = false
    private var isSelLarDrink3 = false
    private var isSelLarDrink4 = false
    private var isSelLarFix1 = false
    private var isSelLarFix2 = false
    private var isSelLarFix3 = false
    private var isSelLarFix4 = false
    private var isSelMedDrink1 = false
    private var isSelMedDrink2 = false
    private var isSelMedDrink3 = false
    private var isSelMedDrink4 = false
    private var isSelRegFix1 = false
    private var isSelRegFix2 = false
    private var isSelRegFix3 = false
    private var isSelRegFix4 = false
    private lateinit var rbtnMeal : RadioGroup
    private lateinit var rbtnChicken : RadioGroup
    private lateinit var rbtnRegFix1 : RadioGroup
    private lateinit var rbtnRegFix2 : RadioGroup
    private lateinit var rbtnRegFix3 : RadioGroup
    private lateinit var rbtnRegFix4 : RadioGroup
    private lateinit var rbtnLarFix1 : RadioGroup
    private lateinit var rbtnLarFix2 : RadioGroup
    private lateinit var rbtnLarFix3 : RadioGroup
    private lateinit var rbtnLarFix4 : RadioGroup
    private lateinit var rbtnDrink : RadioGroup
    private lateinit var rbtnMealDrink : RadioGroup
    private lateinit var rbtnMedDrink1 : RadioGroup
    private lateinit var rbtnMedDrink2 : RadioGroup
    private lateinit var rbtnMedDrink3 : RadioGroup
    private lateinit var rbtnMedDrink4 : RadioGroup
    private lateinit var rbtnLarDrink1 : RadioGroup
    private lateinit var rbtnLarDrink2 : RadioGroup
    private lateinit var rbtnLarDrink3 : RadioGroup
    private lateinit var rbtnLarDrink4 : RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsBinding.inflate(inflater, container, false)

        setupRadios()

        when (Global.isEdit) {
            1 -> {
                setupEditSetup()
            }
            else -> {
                setupCustom()
            }
        }

        rbtnMealDrink.setOnCheckedChangeListener { _, id ->
            run {
                setupMealDrink(id)
            }
        }

        rbtnMeal.setOnCheckedChangeListener { _, id ->
            run {
                setupMeal(id)
            }
        }

        rbtnChicken.setOnCheckedChangeListener { _, id ->
            chicken(id)
        }

        rbtnRegFix1.setOnCheckedChangeListener { _, id ->
            regFix1(id)
        }

        rbtnRegFix2.setOnCheckedChangeListener { _, id ->
            regFix2(id)
        }

        rbtnRegFix3.setOnCheckedChangeListener { _, id ->
            regFix3(id)
        }

        rbtnRegFix4.setOnCheckedChangeListener { _, id ->
            regFix4(id)
        }

        rbtnLarFix1.setOnCheckedChangeListener { _, id ->
            larFix1(id)
        }

        rbtnLarFix2.setOnCheckedChangeListener { _, id ->
            larFix2(id)
        }

        rbtnLarFix3.setOnCheckedChangeListener { _, id ->
            larFix3(id)
        }

        rbtnLarFix4.setOnCheckedChangeListener { _, id ->
            larFix4(id)
        }

        rbtnDrink.setOnCheckedChangeListener { _, id ->
            drink(id)
        }

        rbtnMedDrink1.setOnCheckedChangeListener { _, id ->
            medDrink1(id)
        }

        rbtnMedDrink2.setOnCheckedChangeListener { _, id ->
            medDrink2(id)
        }

        rbtnMedDrink3.setOnCheckedChangeListener { _, id ->
            medDrink3(id)
        }

        rbtnMedDrink4.setOnCheckedChangeListener { _, id ->
            medDrink4(id)
        }

        rbtnLarDrink1.setOnCheckedChangeListener { _, id ->
            larDrink1(id)
        }

        rbtnLarDrink2.setOnCheckedChangeListener { _, id ->
            larDrink2(id)
        }

        rbtnLarDrink3.setOnCheckedChangeListener { _, id ->
            larDrink3(id)
        }

        rbtnLarDrink4.setOnCheckedChangeListener { _, id ->
            larDrink4(id)
        }

        binding.btnBack.setOnClickListener {
            Global.isEdit = 0
            activity?.supportFragmentManager?.popBackStack()
        }

        binding.btnAdd.setOnClickListener {
            add()
        }

        binding.btnRemove.setOnClickListener {
            remove()
        }

        binding.btnAddCart.setOnClickListener {
            addCart()
            activity?.supportFragmentManager?.popBackStack()
        }

        return binding.root
    }

    private fun setupMeal(id : Int) {
        price = when (Global.isEdit) {
            1 -> cart.originalPrice
            else -> itemList[productPos].getPrice() }
        mealReset()
        binding.btnAddCart.isEnabled = false
        isEnabled.clear()
        isEnabled.add(0)
        meal(id)
        varPrice = price
    }

    private fun setupMealDrink(id : Int) {
        rbtnMedDrink1.clearCheck()
        rbtnLarDrink1.clearCheck()
        medDrink1 = null
        larDrink1 = null
        isSelMedDrink1 = false
        isSelLarDrink1 = false
        isEnabled.clear()
        isEnabled.add(0)
        binding.btnAddCart.isEnabled = false
        mealDrink(id)
        varPrice = price
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun setupCustom() {
        binding.btnAddCart.isEnabled = false
        categoryPos = Global.categoryPosition
        productPos = Global.productPosition
        itemList = Global.productList
        price = itemList[productPos].getPrice()
        binding.productImg.setImageResource(itemList[productPos].getImage())
        binding.productName.text = itemList[productPos].getName()
        binding.productPrice.text = "₱${"%,d".format(price)}.00"
        binding.tvTotalValue.text = "₱${"%,d".format(price)}.00"
        custom()
        varPrice = price
    }

    private fun setupEditSetup() {
        cart = cartList[Global.cartItemPos]
        setupEdit()
        binding.btnAddCart.isEnabled = true
        amount = cart.amount
        categoryPos = cart.categoryPos
        productPos = cart.productPos
        price = cart.varPrice
        binding.productImg.setImageResource(cart.image)
        binding.productName.text = cart.name
        binding.tvAmount.text = amount.toString()
        if (amount > 1) binding.btnRemove.setImageResource(R.drawable.remove_red)
        binding.productPrice.text = "₱${"%,d".format(price)}.00"
        edit()
        varPrice = price
        for (i in 1 until cart.increase.size) {
            price += cart.increase[i]
        }
        binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
    }

    private fun setupRadios() {
        rbtnMeal = binding.customMeal.rbtnMeal
        rbtnChicken = binding.customChicken.rbtnChicken
        rbtnRegFix1 = binding.customRegularFixin1.rbtnRegularFixin
        rbtnRegFix2 = binding.customRegularFixin2.rbtnRegularFixin
        rbtnRegFix3 = binding.customRegularFixin3.rbtnRegularFixin
        rbtnRegFix4 = binding.customRegularFixin4.rbtnRegularFixin
        rbtnLarFix1 = binding.customLargeFixin1.rbtnLargeFixin
        rbtnLarFix2 = binding.customLargeFixin2.rbtnLargeFixin
        rbtnLarFix3 = binding.customLargeFixin3.rbtnLargeFixin
        rbtnLarFix4 = binding.customLargeFixin4.rbtnLargeFixin
        rbtnDrink = binding.customDrink.rbtnDrinkSize
        rbtnMealDrink = binding.customMealDrink.rbtnDrinkSize
        rbtnMedDrink1 = binding.customMediumDrink1.rbtnMediumDrinks
        rbtnMedDrink2 = binding.customMediumDrink2.rbtnMediumDrinks
        rbtnMedDrink3 = binding.customMediumDrink3.rbtnMediumDrinks
        rbtnMedDrink4 = binding.customMediumDrink4.rbtnMediumDrinks
        rbtnLarDrink1 = binding.customLargeDrink1.rbtnLargeDrinks
        rbtnLarDrink2 = binding.customLargeDrink2.rbtnLargeDrinks
        rbtnLarDrink3 = binding.customLargeDrink3.rbtnLargeDrinks
        rbtnLarDrink4 = binding.customLargeDrink4.rbtnLargeDrinks
    }

    private fun custom() {
        when(categoryPos) {
            0 -> {
                binding.customChicken.root.visibility = View.VISIBLE
                isEnabled.add(0) }
            1 -> {
                when(productPos) {
                    0 -> customMeal10Price()
                    1 -> customMeal11Price() }
                customChMe() }
            2 -> when(productPos) {
                0 -> {
                    customMeal20Price()
                    customChMe() }
                1 -> {
                    customMealDrinkPrice()
                    customChMeDr()
                    isEnabledAdd(2) } }
            3 -> {
                binding.customChicken.root.visibility = View.VISIBLE
                isEnabled.add(0) }
            4 -> when(productPos) {
                0 -> {
                    customMeal20Price()
                    customChMe() }
                1,2,3,4 -> {
                    customMealDrinkPrice()
                    customChMeDr()
                    isEnabledAdd(2) } }
            5 -> when(productPos) {
                0,2,3 -> {
                    customMealDrinkPrice()
                    binding.customMealDrink.root.visibility = View.VISIBLE
                    isEnabled.add(0) }
                1 -> {
                    customMealDrinkPrice()
                    customChMeDr()
                    isEnabledAdd(2) } }
            6 -> binding.btnAddCart.isEnabled = true
            7 -> {
                customMealDrinkPrice()
                binding.customMealDrink.root.visibility = View.VISIBLE
                isEnabled.add(0) }
            8 -> binding.btnAddCart.isEnabled = true
            9 -> binding.btnAddCart.isEnabled = true
            10 -> when(productPos) {
                0,1,2 -> binding.btnAddCart.isEnabled = true
                3,4,5,6 -> {
                    binding.customDrink.root.visibility = View.VISIBLE
                    isEnabled.add(0) } }
        }
    }

    private fun edit() {
        when(categoryPos) {
            0 -> binding.customChicken.root.visibility = View.VISIBLE
            1 -> {
                customChMe()
                price = cart.originalPrice
                when(productPos) {
                    0 -> {
                        customMeal10Price()
                        when(rbtnMeal.indexOfChild(rbtnMeal.findViewById(cart.selection[0]))) {
                            0 ->customMeal010()
                            1 -> customMeal110()
                            2 -> customMeal210()
                            3 -> customMeal310() } }
                    1 -> {
                        customMeal11Price()
                        when(rbtnMeal.indexOfChild(rbtnMeal.findViewById(cart.selection[0]))) {
                            0 -> customMeal011()
                            1 -> customMeal111()
                            2 -> customMeal211()
                            3 -> customMeal311() } } }
                price = cart.varPrice }
            2 -> {
                price = cart.originalPrice
                when (productPos) {
                    0 -> {
                        customMeal20Price()
                        customChMe()
                        when(rbtnMeal.indexOfChild(rbtnMeal.findViewById(cart.selection[0]))) {
                            0 -> customMeal020()
                            1 -> customMeal120()
                            2 -> customMeal220()
                            3 -> customMeal320() } }
                    1 -> {
                        customMealDrinkPrice()
                        customChMeDr()
                        when(rbtnMealDrink.indexOfChild(rbtnMealDrink.findViewById(cart.selection[1]))) {
                            0 -> customMealDrink0()
                            1 -> customMealDrink1() } } }
                price = cart.varPrice }
            3 -> binding.customChicken.root.visibility = View.VISIBLE
            4 -> {
                price = cart.originalPrice
                when (productPos) {
                    0 -> {
                        customMeal20Price()
                        customChMe()
                        when (rbtnMeal.indexOfChild(rbtnMeal.findViewById(cart.selection[0]))) {
                            0 -> customMeal020()
                            1 -> customMeal120()
                            2 -> customMeal220()
                            3 -> customMeal320() } }
                    1, 2, 3, 4 -> {
                        customMealDrinkPrice()
                        customChMeDr()
                        when (rbtnMealDrink.indexOfChild(rbtnMealDrink.findViewById(cart.selection[1]))) {
                            0 -> customMealDrink0()
                            1 -> customMealDrink1() } } }
                price = cart.varPrice }
            5 -> {
                price = cart.originalPrice
                when (productPos) {
                    0, 2, 3 -> {
                        customMealDrinkPrice()
                        binding.customMealDrink.root.visibility = View.VISIBLE
                        when (rbtnMealDrink.indexOfChild(rbtnMealDrink.findViewById(cart.selection[1]))) {
                            0 -> customMealDrink0()
                            1 -> customMealDrink1() } }
                    1 -> {
                        customMealDrinkPrice()
                        customChMeDr()
                        when (rbtnMealDrink.indexOfChild(rbtnMealDrink.findViewById(cart.selection[1]))) {
                            0 -> customMealDrink0()
                            1 -> customMealDrink1() } } }
                price = cart.varPrice }
            7 -> {
                price = cart.originalPrice
                customMealDrinkPrice()
                binding.customMealDrink.root.visibility = View.VISIBLE
                when(rbtnMealDrink.indexOfChild(rbtnMealDrink.findViewById(cart.selection[1]))) {
                    0 -> customMealDrink0()
                    1 -> customMealDrink1() }
                price = cart.varPrice }
            10 -> when(productPos) {
                3,4,5,6 -> binding.customDrink.root.visibility = View.VISIBLE }
        }
    }

    private fun mealDrink(id: Int) {
        when (rbtnMealDrink.indexOfChild(rbtnMealDrink.findViewById(id))) {
            0 -> {
                customMealDrink0()
                binding.customLargeDrink1.root.visibility = View.GONE
                price -= incMealDrink
                incMealDrink = 0
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price * amount)}.00" }
            1 -> {
                customMealDrink1()
                binding.customMediumDrink1.root.visibility = View.GONE
                price -= incMealDrink
                incMealDrink = 15
                price += incMealDrink
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price * amount)}.00" }
        }
    }

    private fun meal(id: Int) {
        when (rbtnMeal.indexOfChild(rbtnMeal.findViewById(id))) {
            0 -> {
                when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> {
                            customMeal010()
                            hideLarFix()
                            hideLarDrink()
                            isEnabledAdd(6) }
                        1 -> {
                            customMeal011()
                            hideLarFix()
                            hideLarDrink()
                            isEnabledAdd(8) } }
                    2, 4 -> when (productPos) {
                        0 -> {
                            customMeal020()
                            binding.customLargeFixin1.root.visibility = View.GONE
                            binding.customLargeDrink1.root.visibility = View.GONE
                            isEnabledAdd(2) } }
                }
                incMeal = 0
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price * amount)}.00"
            }
            1 -> {
                when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> {
                            customMeal110()
                            hideLarFix()
                            hideMedDrink()
                            isEnabledAdd(6) }
                        1 -> {
                            customMeal111()
                            hideLarFix()
                            hideMedDrink()
                            isEnabledAdd(8) } }
                    2, 4 -> when (productPos) {
                        0 -> {
                            customMeal120()
                            binding.customLargeFixin1.root.visibility = View.GONE
                            binding.customMediumDrink1.root.visibility = View.GONE
                            isEnabledAdd(2) } }
                }
                incMeal = when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> 45
                        1 -> 60
                        else -> 0 }
                    2, 4 -> 15
                    else -> 0 }
                price += incMeal
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price * amount)}.00"
            }
            2 -> {
                when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> {
                            customMeal210()
                            hideRegFix()
                            hideLarDrink()
                            isEnabledAdd(6) }
                        1 -> {
                            customMeal211()
                            hideRegFix()
                            hideLarDrink()
                            isEnabledAdd(8) } }
                    2, 4 -> when (productPos) {
                        0 -> {
                            customMeal220()
                            binding.customRegularFixin1.root.visibility = View.GONE
                            binding.customLargeDrink1.root.visibility = View.GONE
                            isEnabledAdd(2) } }
                }
                incMeal = when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> 105
                        1 -> 140
                        else -> 0 }
                    2, 4 -> 35
                    else -> 0 }
                price += incMeal
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price * amount)}.00"
            }
            3 -> {
                when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> {
                            customMeal310()
                            hideRegFix()
                            hideMedDrink()
                            isEnabledAdd(6) }
                        1 -> {
                            customMeal311()
                            hideRegFix()
                            hideMedDrink()
                            isEnabledAdd(8) } }
                    2, 4 -> when (productPos) {
                        0 -> {
                            customMeal320()
                            binding.customRegularFixin1.root.visibility = View.GONE
                            binding.customMediumDrink1.root.visibility = View.GONE
                            isEnabledAdd(2) } }
                }
                incMeal = when (categoryPos) {
                    1 -> when (productPos) {
                        0 -> 150
                        1 -> 200
                        else -> 0 }
                    2, 4 -> 50
                    else -> 0 }
                price += incMeal
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price * amount)}.00"
            }
        }
    }

    private fun chicken(id: Int) {
        when(rbtnChicken.indexOfChild(rbtnChicken.findViewById(id))) {
            0 -> {
                chicken = "Original"
                if (!isSelChicken) {
                    isSelChicken = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                chicken = "Hot and Crispy"
                if (!isSelChicken) {
                    isSelChicken = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun regFix1(id: Int) {
        when(rbtnRegFix1.indexOfChild(rbtnRegFix1.findViewById(id))) {
            0 -> {
                price -= incRegFix1
                incRegFix1 = 0
                regFix1 = "Regular crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix1) {
                    isSelRegFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incRegFix1
                incRegFix1 = 0
                regFix1 = "Regular buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix1) {
                    isSelRegFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incRegFix1
                incRegFix1 = 0
                regFix1 = "Mushroom soup"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix1) {
                    isSelRegFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incRegFix1
                incRegFix1 = 10
                price += incRegFix1
                regFix1 = "Regular coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix1) {
                    isSelRegFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incRegFix1
                incRegFix1 = 0
                regFix1 = "Regular mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix1) {
                    isSelRegFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            5 -> {
                price -= incRegFix1
                incRegFix1 = 10
                price += incRegFix1
                regFix1 = "Regular macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix1) {
                    isSelRegFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun regFix2(id: Int) {
        when(rbtnRegFix2.indexOfChild(rbtnRegFix2.findViewById(id))) {
            0 -> {
                price -= incRegFix2
                incRegFix2 = 0
                regFix2 = "Regular crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix2) {
                    isSelRegFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incRegFix2
                incRegFix2 = 0
                regFix2 = "Regular buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix2) {
                    isSelRegFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incRegFix2
                incRegFix2 = 0
                regFix2 = "Mushroom soup"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix2) {
                    isSelRegFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incRegFix2
                incRegFix2 = 10
                price += incRegFix2
                regFix2 = "Regular coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix2) {
                    isSelRegFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incRegFix2
                incRegFix2 = 0
                regFix2 = "Regular mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix2) {
                    isSelRegFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            5 -> {
                price -= incRegFix2
                incRegFix2 = 10
                price += incRegFix2
                regFix2 = "Regular macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix2) {
                    isSelRegFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun regFix3(id: Int) {
        when(rbtnRegFix3.indexOfChild(rbtnRegFix3.findViewById(id))) {
            0 -> {
                price -= incRegFix3
                incRegFix3 = 0
                regFix3 = "Regular crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix3) {
                    isSelRegFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incRegFix3
                incRegFix3 = 0
                regFix3 = "Regular buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix3) {
                    isSelRegFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incRegFix3
                incRegFix3 = 0
                regFix3 = "Mushroom soup"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix3) {
                    isSelRegFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incRegFix3
                incRegFix3 = 10
                price += incRegFix3
                regFix3 = "Regular coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix3) {
                    isSelRegFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incRegFix3
                incRegFix3 = 0
                regFix3 = "Regular mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix3) {
                    isSelRegFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            5 -> {
                price -= incRegFix3
                incRegFix3 = 10
                price += incRegFix3
                regFix3 = "Regular macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix3) {
                    isSelRegFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun regFix4(id: Int) {
        when(rbtnRegFix4.indexOfChild(rbtnRegFix4.findViewById(id))) {
            0 -> {
                price -= incRegFix4
                incRegFix4 = 0
                regFix4 = "Regular crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix4) {
                    isSelRegFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incRegFix4
                incRegFix4 = 0
                regFix4 = "Regular buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix4) {
                    isSelRegFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incRegFix4
                incRegFix4 = 0
                regFix4 = "Mushroom soup"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix4) {
                    isSelRegFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incRegFix4
                incRegFix4 = 10
                price += incRegFix4
                regFix4 = "Regular coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix4) {
                    isSelRegFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incRegFix4
                incRegFix4 = 0
                regFix4 = "Regular mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix4) {
                    isSelRegFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            5 -> {
                price -= incRegFix4
                incRegFix4 = 10
                price += incRegFix4
                regFix4 = "Regular macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelRegFix4) {
                    isSelRegFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larFix1(id: Int) {
        when(rbtnLarFix1.indexOfChild(rbtnLarFix1.findViewById(id))) {
            0 -> {
                price -= incLarFix1
                incLarFix1 = 0
                larFix1 = "Large crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix1) {
                    isSelLarFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incLarFix1
                incLarFix1 = 0
                larFix1 = "Large buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix1) {
                    isSelLarFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incLarFix1
                incLarFix1 = 10
                price += incLarFix1
                larFix1 = "Large coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix1) {
                    isSelLarFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incLarFix1
                incLarFix1 = 0
                larFix1 = "Large mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix1) {
                    isSelLarFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incLarFix1
                incLarFix1 = 10
                price += incLarFix1
                larFix1 = "Large macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix1) {
                    isSelLarFix1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larFix2(id: Int) {
        when(rbtnLarFix2.indexOfChild(rbtnLarFix2.findViewById(id))) {
            0 -> {
                price -= incLarFix2
                incLarFix2 = 0
                larFix2 = "Large crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix2) {
                    isSelLarFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incLarFix2
                incLarFix2 = 0
                larFix2 = "Large buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix2) {
                    isSelLarFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incLarFix2
                incLarFix2 = 10
                price += incLarFix2
                larFix2 = "Large coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix2) {
                    isSelLarFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incLarFix2
                incLarFix2 = 0
                larFix2 = "Large mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix2) {
                    isSelLarFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incLarFix2
                incLarFix2 = 10
                price += incLarFix2
                larFix2 = "Large macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix2) {
                    isSelLarFix2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larFix3(id: Int) {
        when(rbtnLarFix3.indexOfChild(rbtnLarFix3.findViewById(id))) {
            0 -> {
                price -= incLarFix3
                incLarFix3 = 0
                larFix3 = "Large crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix3) {
                    isSelLarFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incLarFix3
                incLarFix3 = 0
                larFix3 = "Large buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix3) {
                    isSelLarFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incLarFix3
                incLarFix3 = 10
                price += incLarFix3
                larFix3 = "Large coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix3) {
                    isSelLarFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incLarFix3
                incLarFix3 = 0
                larFix3 = "Large mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix3) {
                    isSelLarFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incLarFix3
                incLarFix3 = 10
                price += incLarFix3
                larFix3 = "Large macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix3) {
                    isSelLarFix3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larFix4(id: Int) {
        when(rbtnLarFix4.indexOfChild(rbtnLarFix4.findViewById(id))) {
            0 -> {
                price -= incLarFix4
                incLarFix4 = 0
                larFix4 = "Large crispy fries"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix4) {
                    isSelLarFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price -= incLarFix4
                incLarFix4 = 0
                larFix4 = "Large buttered corn"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix4) {
                    isSelLarFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price -= incLarFix4
                incLarFix4 = 10
                price += incLarFix4
                larFix4 = "Large coleslaw"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix4) {
                    isSelLarFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                price -= incLarFix4
                incLarFix4 = 0
                larFix4 = "Large mashed potato"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix4) {
                    isSelLarFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                price -= incLarFix4
                incLarFix4 = 10
                price += incLarFix4
                larFix4 = "Large macaroni"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelLarFix4) {
                    isSelLarFix4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun drink(id: Int) {
        when(rbtnDrink.indexOfChild(rbtnDrink.findViewById(id))) {
            0 -> {
                price = 55
                drink = "Regular"
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelDrink) {
                    isSelDrink = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                price = 75
                drink = "Medium"
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelDrink) {
                    isSelDrink = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                price = 80
                drink = "Large"
                binding.productPrice.text = "₱${"%,d".format(price)}.00"
                binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
                if (!isSelDrink) {
                    isSelDrink = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun medDrink1(id: Int) {
        when(rbtnMedDrink1.indexOfChild(rbtnMedDrink1.findViewById(id))) {
            0 -> {
                medDrink1 = "Medium unsweetened iced tea"
                if (!isSelMedDrink1) {
                    isSelMedDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                medDrink1 = "Medium coke original"
                if (!isSelMedDrink1) {
                    isSelMedDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                medDrink1 = "Medium coke zero"
                if (!isSelMedDrink1) {
                    isSelMedDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                medDrink1 = "Medium royal"
                if (!isSelMedDrink1) {
                    isSelMedDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                medDrink1 = "Medium sprite"
                if (!isSelMedDrink1) {
                    isSelMedDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun medDrink2(id: Int) {
        when(rbtnMedDrink2.indexOfChild(rbtnMedDrink2.findViewById(id))) {
            0 -> {
                medDrink2 = "Medium unsweetened iced tea"
                if (!isSelMedDrink2) {
                    isSelMedDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                medDrink2 = "Medium coke original"
                if (!isSelMedDrink2) {
                    isSelMedDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                medDrink2 = "Medium coke zero"
                if (!isSelMedDrink2) {
                    isSelMedDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                medDrink2 = "Medium royal"
                if (!isSelMedDrink2) {
                    isSelMedDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                medDrink2 = "Medium sprite"
                if (!isSelMedDrink2) {
                    isSelMedDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun medDrink3(id: Int) {
        when(rbtnMedDrink3.indexOfChild(rbtnMedDrink3.findViewById(id))) {
            0 -> {
                medDrink3 = "Medium unsweetened iced tea"
                if (!isSelMedDrink3) {
                    isSelMedDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                medDrink3 = "Medium coke original"
                if (!isSelMedDrink3) {
                    isSelMedDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                medDrink3 = "Medium coke zero"
                if (!isSelMedDrink3) {
                    isSelMedDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                medDrink3 = "Medium royal"
                if (!isSelMedDrink3) {
                    isSelMedDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                medDrink3 = "Medium sprite"
                if (!isSelMedDrink3) {
                    isSelMedDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun medDrink4(id: Int) {
        when(rbtnMedDrink4.indexOfChild(rbtnMedDrink4.findViewById(id))) {
            0 -> {
                medDrink4 = "Medium unsweetened iced tea"
                if (!isSelMedDrink4) {
                    isSelMedDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                medDrink4 = "Medium coke original"
                if (!isSelMedDrink4) {
                    isSelMedDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                medDrink4 = "Medium coke zero"
                if (!isSelMedDrink4) {
                    isSelMedDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                medDrink4 = "Medium royal"
                if (!isSelMedDrink4) {
                    isSelMedDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                medDrink4 = "Medium sprite"
                if (!isSelMedDrink4) {
                    isSelMedDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larDrink1(id : Int) {
        when(rbtnLarDrink1.indexOfChild(rbtnLarDrink1.findViewById(id))) {
            0 -> {
                larDrink1 = "Large unsweetened iced tea"
                if (!isSelLarDrink1) {
                    isSelLarDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                larDrink1 = "Large coke original"
                if (!isSelLarDrink1) {
                    isSelLarDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                larDrink1 = "Large coke zero"
                if (!isSelLarDrink1) {
                    isSelLarDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                larDrink1 = "Large royal"
                if (!isSelLarDrink1) {
                    isSelLarDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                larDrink1 = "Large sprite"
                if (!isSelLarDrink1) {
                    isSelLarDrink1 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larDrink2(id : Int) {
        when(rbtnLarDrink2.indexOfChild(rbtnLarDrink2.findViewById(id))) {
            0 -> {
                larDrink2 = "Large unsweetened iced tea"
                if (!isSelLarDrink2) {
                    isSelLarDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                larDrink2 = "Large coke original"
                if (!isSelLarDrink2) {
                    isSelLarDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                larDrink2 = "Large coke zero"
                if (!isSelLarDrink2) {
                    isSelLarDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                larDrink2 = "Large royal"
                if (!isSelLarDrink2) {
                    isSelLarDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                larDrink2 = "Large sprite"
                if (!isSelLarDrink2) {
                    isSelLarDrink2 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larDrink3(id : Int) {
        when(rbtnLarDrink3.indexOfChild(rbtnLarDrink3.findViewById(id))) {
            0 -> {
                larDrink3 = "Large unsweetened iced tea"
                if (!isSelLarDrink3) {
                    isSelLarDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                larDrink3 = "Large coke original"
                if (!isSelLarDrink3) {
                    isSelLarDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                larDrink3 = "Large coke zero"
                if (!isSelLarDrink3) {
                    isSelLarDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                larDrink3 = "Large royal"
                if (!isSelLarDrink3) {
                    isSelLarDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                larDrink3 = "Large sprite"
                if (!isSelLarDrink3) {
                    isSelLarDrink3 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun larDrink4(id : Int) {
        when(rbtnLarDrink4.indexOfChild(rbtnLarDrink4.findViewById(id))) {
            0 -> {
                larDrink4 = "Large unsweetened iced tea"
                if (!isSelLarDrink4) {
                    isSelLarDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            1 -> {
                larDrink4 = "Large coke original"
                if (!isSelLarDrink4) {
                    isSelLarDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            2 -> {
                larDrink4 = "Large coke zero"
                if (!isSelLarDrink4) {
                    isSelLarDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            3 -> {
                larDrink4 = "Large royal"
                if (!isSelLarDrink4) {
                    isSelLarDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } }
            4 -> {
                larDrink4 = "Large sprite"
                if (!isSelLarDrink4) {
                    isSelLarDrink4 = true
                    isEnabled.removeAt(isEnabled.lastIndex) } } }
        binding.btnAddCart.isEnabled = isEnabled.isEmpty()
    }

    private fun add() {
        if (Global.isEdit == 0) {
            if (amount + 1 <= itemList[productPos].getQuantity()) amount += 1
            if (amount == itemList[productPos].getQuantity())
                binding.btnAdd.setImageResource(R.drawable.add_gray)
        } else {
            if (amount + 1 <= cartList[Global.cartItemPos].quantity) amount += 1
            if (amount == cartList[Global.cartItemPos].quantity)
                binding.btnAdd.setImageResource(R.drawable.add_gray)
        }
        binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
        binding.tvAmount.text = amount.toString()
        binding.btnRemove.setImageResource(R.drawable.remove_red)
    }

    private fun remove() {
        if (amount > 1) {
            binding.btnAdd.setImageResource(R.drawable.add)
            amount -= 1
            binding.tvAmount.text = amount.toString()
        }
        if (amount == 1) binding.btnRemove.setImageResource(R.drawable.remove_gray)
        binding.tvTotalValue.text = "₱${"%,d".format(price*amount)}.00"
    }

    private fun addCart() {
        val customList = mutableListOf<String?>()
        for (custom in listOf(chicken, regFix1, regFix2, regFix3, regFix4, larFix1,
            larFix2, larFix3, larFix4, drink, medDrink1, medDrink2, medDrink3,
            medDrink4, larDrink1, larDrink2, larDrink3, larDrink4)) {
            customList.add(custom)
        }
        val selectionList = mutableListOf<Int>()
        for (rbtn in listOf(rbtnMeal, rbtnMealDrink, rbtnChicken, rbtnRegFix1, rbtnRegFix2,
            rbtnRegFix3, rbtnRegFix4, rbtnLarFix1, rbtnLarFix2, rbtnLarFix3, rbtnLarFix4,
            rbtnDrink, rbtnMedDrink1, rbtnMedDrink2, rbtnMedDrink3, rbtnMedDrink4,
            rbtnLarDrink1, rbtnLarDrink2, rbtnLarDrink3, rbtnLarDrink4)) {
            selectionList.add(rbtn.checkedRadioButtonId)
        }
        val incList = mutableListOf<Int>()
        for (inc in listOf(incMeal, incRegFix1, incRegFix2, incRegFix3, incRegFix4, incMealDrink,
            incLarFix1, incLarFix2, incLarFix3, incLarFix4)) {
            incList.add(inc)
        }
        val selectList = mutableListOf<Boolean>()
        for (sel in listOf(isSelChicken, isSelLarDrink1, isSelLarDrink2, isSelLarDrink3,
            isSelLarDrink4, isSelLarFix1, isSelLarFix2, isSelLarFix3, isSelLarFix4, isSelDrink,
            isSelMedDrink1, isSelMedDrink2, isSelMedDrink3, isSelMedDrink4, isSelRegFix1,
            isSelRegFix2, isSelRegFix3, isSelRegFix4)) {
            selectList.add(sel)
        }
        when (Global.isEdit) {
            1 -> {
                cartList.removeAt(Global.cartItemPos)
                cartList.add(CartListItem(cart.id, cart.image, cart.name, customList, selectionList, incList,
                    selectList, price * amount, amount, price, categoryPos, productPos,
                    cart.originalPrice, varPrice, cart.quantity))
                Global.isEdit = 0
            }
            else -> cartList.add(CartListItem(itemList[productPos].getID(), itemList[productPos].getImage(), itemList[productPos].getName(),
                customList, selectionList, incList, selectList, price*amount, amount, price, categoryPos,
                productPos, itemList[productPos].getPrice(), varPrice, itemList[productPos].getQuantity()))
        }
    }

    private fun setupEdit() {
        chicken = cart.customs[0]
        regFix1 = cart.customs[1]
        regFix2 = cart.customs[2]
        regFix3 = cart.customs[3]
        regFix4 = cart.customs[4]
        larFix1 = cart.customs[5]
        larFix2 = cart.customs[6]
        larFix3 = cart.customs[7]
        larFix4 = cart.customs[8]
        drink = cart.customs[9]
        medDrink1 = cart.customs[10]
        medDrink2 = cart.customs[11]
        medDrink3 = cart.customs[12]
        medDrink4 = cart.customs[13]
        larDrink1 = cart.customs[14]
        larDrink2 = cart.customs[15]
        larDrink3 = cart.customs[16]
        larDrink4 = cart.customs[17]

        rbtnMeal.check(cart.selection[0])
        rbtnMealDrink.check(cart.selection[1])
        rbtnChicken.check(cart.selection[2])
        rbtnRegFix1.check(cart.selection[3])
        rbtnRegFix2.check(cart.selection[4])
        rbtnRegFix3.check(cart.selection[5])
        rbtnRegFix4.check(cart.selection[6])
        rbtnLarFix1.check(cart.selection[7])
        rbtnLarFix2.check(cart.selection[8])
        rbtnLarFix3.check(cart.selection[9])
        rbtnLarFix4.check(cart.selection[10])
        rbtnDrink.check(cart.selection[11])
        rbtnMedDrink1.check(cart.selection[12])
        rbtnMedDrink2.check(cart.selection[13])
        rbtnMedDrink3.check(cart.selection[14])
        rbtnMedDrink4.check(cart.selection[15])
        rbtnLarDrink1.check(cart.selection[16])
        rbtnLarDrink2.check(cart.selection[17])
        rbtnLarDrink3.check(cart.selection[18])
        rbtnLarDrink4.check(cart.selection[19])

        incMeal = cart.increase[0]
        incRegFix1 = cart.increase[1]
        incRegFix2 = cart.increase[2]
        incRegFix3 = cart.increase[3]
        incRegFix4 = cart.increase[4]
        incMealDrink = cart.increase[5]
        incLarFix1 = cart.increase[6]
        incLarFix2 = cart.increase[7]
        incLarFix3 = cart.increase[8]
        incLarFix4 = cart.increase[9]

        isSelChicken = cart.select[0]
        isSelLarDrink1 = cart.select[1]
        isSelLarDrink2 = cart.select[2]
        isSelLarDrink3 = cart.select[3]
        isSelLarDrink4 = cart.select[4]
        isSelLarFix1 = cart.select[5]
        isSelLarFix2 = cart.select[6]
        isSelLarFix3 = cart.select[7]
        isSelLarFix4 = cart.select[8]
        isSelDrink = cart.select[9]
        isSelMedDrink1 = cart.select[10]
        isSelMedDrink2 = cart.select[11]
        isSelMedDrink3 = cart.select[12]
        isSelMedDrink4 = cart.select[13]
        isSelRegFix1 = cart.select[14]
        isSelRegFix2 = cart.select[15]
        isSelRegFix3 = cart.select[16]
        isSelRegFix4 = cart.select[17]
    }

    private fun isEnabledAdd(count: Int) {
        repeat(count) {
            isEnabled.add(0)
        }
    }

    private fun hideRegFix() {
        binding.customRegularFixin1.root.visibility = View.GONE
        binding.customRegularFixin2.root.visibility = View.GONE
        binding.customRegularFixin3.root.visibility = View.GONE
        binding.customRegularFixin4.root.visibility = View.GONE
    }

    private fun hideMedDrink() {
        binding.customMediumDrink1.root.visibility = View.GONE
        binding.customMediumDrink2.root.visibility = View.GONE
        binding.customMediumDrink3.root.visibility = View.GONE
        binding.customMediumDrink4.root.visibility = View.GONE
    }

    private fun hideLarFix() {
        binding.customLargeFixin1.root.visibility = View.GONE
        binding.customLargeFixin2.root.visibility = View.GONE
        binding.customLargeFixin3.root.visibility = View.GONE
        binding.customLargeFixin4.root.visibility = View.GONE
    }

    private fun hideLarDrink() {
        binding.customLargeDrink1.root.visibility = View.GONE
        binding.customLargeDrink2.root.visibility = View.GONE
        binding.customLargeDrink3.root.visibility = View.GONE
        binding.customLargeDrink4.root.visibility = View.GONE
    }

    private fun customMeal320() {
        binding.customLargeFixin1.tvCustomText.text = "Choice of Large Fixin"
        binding.customLargeDrink1.tvCustomText.text = "Choice of Large Drink"
        binding.customLargeFixin1.root.visibility = View.VISIBLE
        binding.customLargeDrink1.root.visibility = View.VISIBLE
    }

    private fun customMeal220() {
        binding.customLargeFixin1.tvCustomText.text = "Choice of Large Fixin"
        binding.customMediumDrink1.tvCustomText.text = "Choice of Medium Drink"
        binding.customLargeFixin1.root.visibility = View.VISIBLE
        binding.customMediumDrink1.root.visibility = View.VISIBLE
    }

    private fun customMeal120() {
        binding.customRegularFixin1.tvCustomText.text = "Choice of Regular Fixin"
        binding.customLargeDrink1.tvCustomText.text = "Choice of Large Drink"
        binding.customRegularFixin1.root.visibility = View.VISIBLE
        binding.customLargeDrink1.root.visibility = View.VISIBLE
    }

    private fun customMeal020() {
        binding.customRegularFixin1.tvCustomText.text = "Choice of Regular Fixin"
        binding.customMediumDrink1.tvCustomText.text = "Choice of Medium Drink"
        binding.customRegularFixin1.root.visibility = View.VISIBLE
        binding.customMediumDrink1.root.visibility = View.VISIBLE
    }

    private fun mealReset() {
        rbtnChicken.clearCheck()
        rbtnRegFix1.clearCheck()
        rbtnRegFix2.clearCheck()
        rbtnRegFix3.clearCheck()
        rbtnRegFix4.clearCheck()
        rbtnLarFix1.clearCheck()
        rbtnLarFix2.clearCheck()
        rbtnLarFix3.clearCheck()
        rbtnLarFix4.clearCheck()
        rbtnMedDrink1.clearCheck()
        rbtnMedDrink2.clearCheck()
        rbtnMedDrink3.clearCheck()
        rbtnMedDrink4.clearCheck()
        rbtnLarDrink1.clearCheck()
        rbtnLarDrink2.clearCheck()
        rbtnLarDrink3.clearCheck()
        rbtnLarDrink4.clearCheck()
        chicken = null
        regFix1 = null
        regFix2 = null
        regFix3 = null
        regFix4 = null
        larFix1 = null
        larFix2 = null
        larFix3 = null
        larFix4 = null
        medDrink1 = null
        medDrink2 = null
        medDrink3 = null
        medDrink4 = null
        larDrink1 = null
        larDrink2 = null
        larDrink3 = null
        larDrink4 = null
        incLarFix1 = 0
        incLarFix2 = 0
        incLarFix3 = 0
        incLarFix4 = 0
        incRegFix1 = 0
        incRegFix2 = 0
        incRegFix3 = 0
        incRegFix4 = 0
        isSelChicken = false
        isSelLarDrink1 = false
        isSelLarDrink2 = false
        isSelLarDrink3 = false
        isSelLarDrink4 = false
        isSelLarFix1 = false
        isSelLarFix2 = false
        isSelLarFix3 = false
        isSelLarFix4 = false
        isSelDrink = false
        isSelMedDrink1 = false
        isSelMedDrink2 = false
        isSelMedDrink3 = false
        isSelMedDrink4 = false
        isSelRegFix1 = false
        isSelRegFix2 = false
        isSelRegFix3 = false
        isSelRegFix4 = false
    }

    private fun customChMe() {
        binding.customChicken.root.visibility = View.VISIBLE
        binding.customMeal.root.visibility = View.VISIBLE
    }

    private fun customChMeDr() {
        binding.customChicken.root.visibility = View.VISIBLE
        binding.customMealDrink.root.visibility = View.VISIBLE
    }

    private fun customMealDrink1() {
        binding.customLargeDrink1.tvCustomText.text = "Choice of Large Drink"
        binding.customLargeDrink1.root.visibility = View.VISIBLE
    }

    private fun customMealDrink0() {
        binding.customMediumDrink1.tvCustomText.text = "Choice of Medium Drink"
        binding.customMediumDrink1.root.visibility = View.VISIBLE
    }

    private fun customMealDrinkPrice() {
        binding.customMealDrink.tvMediumDrinkPrice.text = "₱${"%,d".format(price)}.00"
        binding.customMealDrink.tvLargeDrinkPrice.text = "₱${"%,d".format(price+15)}.00"
    }

    private fun customMeal20Price() {
        binding.customMeal.tvRegFixMedDrinkPrice.text = "₱${"%,d".format(price)}.00"
        binding.customMeal.tvRegFixLarDrinkPrice.text = "₱${"%,d".format(price+15)}.00"
        binding.customMeal.tvLarFixMedDrinkPrice.text = "₱${"%,d".format(price+35)}.00"
        binding.customMeal.tvLarFixLarDrinkPrice.text = "₱${"%,d".format(price+50)}.00"
    }

    private fun customMeal11Price() {
        binding.customMeal.tvRegFixMedDrinkPrice.text = "₱${"%,d".format(price)}.00"
        binding.customMeal.tvRegFixLarDrinkPrice.text = "₱${"%,d".format(price+60)}.00"
        binding.customMeal.tvLarFixMedDrinkPrice.text = "₱${"%,d".format(price+140)}.00"
        binding.customMeal.tvLarFixLarDrinkPrice.text = "₱${"%,d".format(price+200)}.00"
    }

    private fun customMeal10Price() {
        binding.customMeal.tvRegFixMedDrinkPrice.text = "₱${"%,d".format(price)}.00"
        binding.customMeal.tvRegFixLarDrinkPrice.text = "₱${"%,d".format(price+45)}.00"
        binding.customMeal.tvLarFixMedDrinkPrice.text = "₱${"%,d".format(price+105)}.00"
        binding.customMeal.tvLarFixLarDrinkPrice.text = "₱${"%,d".format(price+150)}.00"
    }

    private fun customLarFix() {
        binding.customLargeFixin1.tvCustomText.text = "Choice of 1st Large Fixin"
        binding.customLargeFixin2.tvCustomText.text = "Choice of 2nd Large Fixin"
        binding.customLargeFixin3.tvCustomText.text = "Choice of 3rd Large Fixin"
        binding.customLargeFixin1.root.visibility = View.VISIBLE
        binding.customLargeFixin2.root.visibility = View.VISIBLE
        binding.customLargeFixin3.root.visibility = View.VISIBLE
    }

    private fun customLarDrink() {
        binding.customLargeDrink1.tvCustomText.text = "Choice of 1st Large Drink"
        binding.customLargeDrink2.tvCustomText.text = "Choice of 2nd Large Drink"
        binding.customLargeDrink3.tvCustomText.text = "Choice of 3rd Large Drink"
        binding.customLargeDrink1.root.visibility = View.VISIBLE
        binding.customLargeDrink2.root.visibility = View.VISIBLE
        binding.customLargeDrink3.root.visibility = View.VISIBLE
    }

    private fun customRegFix() {
        binding.customRegularFixin1.tvCustomText.text = "Choice of 1st Regular Fixin"
        binding.customRegularFixin2.tvCustomText.text = "Choice of 2nd Regular Fixin"
        binding.customRegularFixin3.tvCustomText.text = "Choice of 3rd Regular Fixin"
        binding.customRegularFixin1.root.visibility = View.VISIBLE
        binding.customRegularFixin2.root.visibility = View.VISIBLE
        binding.customRegularFixin3.root.visibility = View.VISIBLE
    }

    private fun customMedDrink() {
        binding.customMediumDrink1.tvCustomText.text = "Choice of 1st Medium Drink"
        binding.customMediumDrink2.tvCustomText.text = "Choice of 2nd Medium Drink"
        binding.customMediumDrink3.tvCustomText.text = "Choice of 3rd Medium Drink"
        binding.customMediumDrink1.root.visibility = View.VISIBLE
        binding.customMediumDrink2.root.visibility = View.VISIBLE
        binding.customMediumDrink3.root.visibility = View.VISIBLE
    }

    private fun customMeal311() {
        customLarFix()
        binding.customLargeFixin4.tvCustomText.text = "Choice of 4th Large Fixin"
        binding.customLargeFixin4.root.visibility = View.VISIBLE
        customLarDrink()
        binding.customLargeDrink4.tvCustomText.text = "Choice of 4th Large Drink"
        binding.customLargeDrink4.root.visibility = View.VISIBLE
    }

    private fun customMeal211() {
        customLarFix()
        binding.customLargeFixin4.tvCustomText.text = "Choice of 4th Large Fixin"
        binding.customLargeFixin4.root.visibility = View.VISIBLE
        customMedDrink()
        binding.customMediumDrink4.tvCustomText.text = "Choice of 4th Medium Drink"
        binding.customMediumDrink4.root.visibility = View.VISIBLE
    }

    private fun customMeal111() {
        customRegFix()
        binding.customRegularFixin4.tvCustomText.text = "Choice of 4th Regular Fixin"
        binding.customRegularFixin4.root.visibility = View.VISIBLE
        customLarDrink()
        binding.customLargeDrink4.tvCustomText.text = "Choice of 4th Large Drink"
        binding.customLargeDrink4.root.visibility = View.VISIBLE
    }

    private fun customMeal011() {
        customRegFix()
        binding.customRegularFixin4.tvCustomText.text = "Choice of 4th Regular Fixin"
        binding.customRegularFixin4.root.visibility = View.VISIBLE
        customMedDrink()
        binding.customMediumDrink4.tvCustomText.text = "Choice of 4th Medium Drink"
        binding.customMediumDrink4.root.visibility = View.VISIBLE
    }

    private fun customMeal010() {
        customRegFix()
        customMedDrink()
    }

    private fun customMeal110() {
        customRegFix()
        customLarDrink()
    }

    private fun customMeal210() {
        customLarFix()
        customMedDrink()
    }

    private fun customMeal310() {
        customLarFix()
        customLarDrink()
    }
}