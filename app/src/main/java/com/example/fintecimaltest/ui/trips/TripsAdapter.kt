package com.example.fintecimaltest.ui.trips


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fintecimaltest.data.entities.Trip
import com.example.fintecimaltest.databinding.ItemTripBinding

class TripsAdapter(private val listener: TripItemListener) : RecyclerView.Adapter<TripViewHolder>() {

    interface TripItemListener {
        fun onClickedTrip(TripId: Int)
    }

    private val items = ArrayList<Trip>()

    fun setItems(items: ArrayList<Trip>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding: ItemTripBinding = ItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) = holder.bind(items[position])
}

class TripViewHolder(private val itemBinding: ItemTripBinding, private val listener: TripsAdapter.TripItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var Trip: Trip

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Trip) {
        this.Trip = item
        if(item.visited){
            itemBinding.visited.text = "Visitada"
        }
        else
        {
            itemBinding.visited.text = "Pendiente"
        }
        itemBinding.visited.text = item.visited.toString()
        itemBinding.streetName.text = item.streetName
        itemBinding.suburb.text = item.suburb

    }

    override fun onClick(v: View?) {
        listener.onClickedTrip(Trip.id)
    }
}