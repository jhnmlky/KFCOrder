package com.kfccorp.kfcorder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.kfccorp.kfcorder.R
import com.kfccorp.kfcorder.adapters.*
import com.kfccorp.kfcorder.databinding.MenuBinding
import com.kfccorp.kfcorder.interfaces.*
import com.kfccorp.kfcorder.models.*
import com.kfccorp.kfcorder.viewholders.CategoryViewHolder

@SuppressLint("SetTextI18n")
class Menu : Fragment(), CategoryClickListener, ProductClickListener, CartClickListener {

    private lateinit var binding : MenuBinding
    private var currentItems = mutableListOf<ProductInterface>()
    private lateinit var categoryAdapter: CategoryAdapter
    private var categoryPosition : Int = 0
    private var total : Int = 0
    private var toast : Toast? = null
    private var pos : Int = 0
    private val circularCategoryList = ArrayList(categoryList + categoryList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MenuBinding.inflate(inflater, container, false)

        categoryPosition = Global.categoryPosition                                                      // SAVE PREVIOUS CATEGORY POSITION TO OBJECT CLASS

        binding.btnCheck.isEnabled = cartList.isNotEmpty()                                              // LOCK BUTTON IF LIST CART LIST IF EMPTY ELSE UNLOCK

        binding.rlSummary.layoutParams.height = ScreenSize.SCREEN_HEIGHT/4
        total = 0
        for (cartItem in cartList) {
            total += cartItem.total
        }
        if (total > 0) binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"

        if (cartList.isNotEmpty()) {
            binding.tvSummary.visibility = View.GONE
        } else {
            binding.tvSummary.visibility = View.VISIBLE
        }

        binding.btnBack.setOnClickListener {
            Global.previous = 11
            Global.categoryPosition = 0
            cartList.clear()
            activity?.supportFragmentManager?.popBackStack()                                            // GO BACK TO START SCREEN
        }

        binding.btnCart.setOnClickListener {
            if (cartList.isNotEmpty()) {
                findNavController().navigate(MenuDirections.actionMenuToCart())                         // NAVIGATE TO CART
            } else {
                toast?.cancel()
                toast = Toast.makeText(requireContext(), "Cart is Empty", Toast.LENGTH_SHORT)
                toast?.show()
            }
        }

        binding.btnCheck.setOnClickListener {
            findNavController().navigate(MenuDirections.actionMenuToCart())                             // NAVIGATE TO CART
        }

        binding.rvCart.layoutManager = LinearLayoutManager(requireActivity())                           // SET LAYOUT FOR CART ITEM LIST
        binding.rvCart.adapter = CartItemAdapter(cartList, this)                           // SET ADAPTER FOR CART ITEM LIST

        val layoutManager = CircularLinearLayoutManager(requireActivity(),                              // SET LAYOUT FOR CATEGORY LIST
            LinearLayoutManager.HORIZONTAL, false)
        layoutManager.scrollToPositionWithOffset(Global.previous,
            (ScreenSize.SCREEN_WIDTH/2+20) - (ScreenSize.SCREEN_WIDTH/(ScreenSize.SCREEN_WIDTH/125)))    // SCROLL TO INITIAL POSITION
        categoryAdapter = CategoryAdapter(circularCategoryList, this,                   // SET ADAPTER FOR CATEGORY LIST
            layoutManager)

        binding.rvCategories.layoutManager = layoutManager
        binding.rvCategories.adapter = categoryAdapter

        binding.rvProducts.layoutManager = GridLayoutManager(activity,                                  // SET LAYOUT FOR PRODUCT LISTS
            ScreenSize.SCREEN_WIDTH/320)
        when(categoryPosition) {                                                                        // SET ADAPTERS FOR ALL PRODUCT LISTS
            0 ->  binding.rvProducts.adapter = ProductsAdapter(cat1Products, this)
            1 ->  binding.rvProducts.adapter = ProductsAdapter(cat2Products, this)
            2 ->  binding.rvProducts.adapter = ProductsAdapter(cat3Products, this)
            3 ->  binding.rvProducts.adapter = ProductsAdapter(cat4Products, this)
            4 ->  binding.rvProducts.adapter = ProductsAdapter(cat5Products, this)
            5 ->  binding.rvProducts.adapter = ProductsAdapter(cat6Products, this)
            6 ->  binding.rvProducts.adapter = ProductsAdapter(cat7Products, this)
            7 ->  binding.rvProducts.adapter = ProductsAdapter(cat8Products, this)
            8 ->  binding.rvProducts.adapter = ProductsAdapter(cat9Products, this)
            9 ->  binding.rvProducts.adapter = ProductsAdapter(cat10Products, this)
            10 ->  binding.rvProducts.adapter = ProductsAdapter(cat11Products, this)
        }

        return binding.root
    }

    override fun onCategoryClick(position: Int) {                                                       // HANDLE CLICK EVENTS ON CATEGORY ITEMS
        pos = position % categoryList.size
        when(pos) {
            0 -> currentItems = cat1Products.map {
                Cat1Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            1 -> currentItems = cat2Products.map {
                Cat2Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            2 -> currentItems = cat3Products.map {
                Cat3Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            3 -> currentItems = cat4Products.map {
                Cat4Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            4 -> currentItems = cat5Products.map {
                Cat5Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            5 -> currentItems = cat6Products.map {
                Cat6Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            6 -> currentItems = cat7Products.map {
                Cat7Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            7 -> currentItems = cat8Products.map {
                Cat8Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            8 -> currentItems = cat9Products.map {
                Cat9Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            9 -> currentItems = cat10Products.map {
                Cat10Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
            10 -> currentItems = cat11Products.map {
                Cat11Products(it._id, it._img, it._name, it._price, it._quantity)
            }.toMutableList()
        }
        binding.rvProducts.adapter = ProductsAdapter(currentItems, this)                // UPDATE PRODUCT ITEM LIST
    }

    override fun getViewHolder(position: Int): List<CategoryViewHolder> {
        return categoryAdapter.getCategoryViewHolders(position)
    }

    override fun onProductClick(position: Int) {                                                        // HANDLE PRODUCT ITEM CLICK
        val itemList = when (binding.rvProducts.adapter) {
            is ProductsAdapter -> (binding.rvProducts.adapter as ProductsAdapter).products              // GET PRODUCT LIST
            else -> emptyList()
        }
        Global.productPosition = position                                                               // SAVE DATA TO OBJECT CLASS
        Global.productList = itemList
        Global.categoryPosition = pos
        findNavController().navigate(MenuDirections.actionMenuToDetails())                              // NAVIGATE TO PRODUCT DETAILS
    }

    override fun onRemoveClick(                                                                         // HANDLE REDUCE ITEM AMOUNT CLICK ON CART ITEMS
        position: Int,
        amount: Int,
        price: Int,
        tvAmount: TextView,
        tvPrice: TextView,
        btnRemove : ImageButton,
        btnAdd: ImageButton
    ) {
        btnAdd.setImageResource(R.drawable.add_cart)
        var amount1 = amount
        var price1 = price
        if (amount1 > 1) {
            amount1 -=1
            price1 *= amount1
            val total1 = price1
            cartList[position].amount = amount1                                                         // SAVE NEW VALUES TO ITEM IN CART LIST
            cartList[position].total = total1
            tvAmount.text = amount1.toString()
            tvPrice.text = "₱${"%,d".format(total1)}.00"
            total = 0
            for (cartItem in cartList) {
                total += cartItem.total                                                                     // SUMMATION OF ALL CART LIST ITEM PRICE
            }
            binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"
        }
        if (amount1 == 1) btnRemove.setImageResource(R.drawable.remove_cart_gray)
    }

    override fun onAddClick(                                                                            // HANDLE ADD ITEM AMOUNT CLICK ON CART ITEMS
        position: Int,
        amount: Int,
        price: Int,
        tvAmount: TextView,
        tvPrice: TextView,
        btnRemove : ImageButton,
        btnAdd: ImageButton
    ) {
        btnRemove.setImageResource(R.drawable.remove_cart)
        var amount1 = amount
        var price1 = price
        if (amount1 + 1 <= cartList[position].quantity) {
            amount1 +=1
            price1 *= amount1
            val total1 = price1
            cartList[position].amount = amount1                                                             // SAVE NEW VALUES TO ITEM IN CART LIST
            cartList[position].total = total1
            tvAmount.text = amount1.toString()
            tvPrice.text = "₱${"%,d".format(total1)}.00"
            total = 0
            for (cartItem in cartList) {
                total += cartItem.total                                                                     // SUMMATION OF ALL CART LIST ITEM PRICE
            }
            binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"
        }
        if (amount1 == cartList[position].quantity)
            btnAdd.setImageResource(R.drawable.add_cart_gray)
    }

    override fun onDeleteClick(position: Int) {                                                         // HANDLE DELETE ITEM CLICK ON CART ITEMS
        cartList.removeAt(position)                                                                     // DELETE ITEM FROM CART LIST
        binding.rvCart.adapter = CartItemAdapter(cartList, this)                           // UPDATE CART ITEMS
        if (cartList.isEmpty()) {
            binding.btnCheck.isEnabled = false
            binding.tvSummary.visibility = View.VISIBLE
        }
        total = 0
        for (cartItem in cartList) {
            total += cartItem.total                                                                     // SUMMATION OF ALL CART LIST ITEM PRICE
        }
        if (total > 0) {
            binding.tvTotalValue.text = "₱${"%,d".format(total)}.00"
        } else {
            binding.tvTotalValue.text = "Total"
        }
    }

    override fun onEditClick(position: Int) {
        Global.isEdit = 1
        Global.cartItemPos = position
        findNavController().navigate(MenuDirections.actionMenuToDetails())
    }
}