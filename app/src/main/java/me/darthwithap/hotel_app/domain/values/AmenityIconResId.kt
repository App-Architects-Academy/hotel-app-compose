package me.darthwithap.hotel_app.domain.values

import androidx.annotation.DrawableRes
import me.darthwithap.hotel_app.R

enum class AmenityIconResId(@DrawableRes val resId: Int) {
  AIR_CONDITIONER(R.drawable.ic_ac),
  CAMERA(R.drawable.ic_camera),
  SECURITY_CAMERA(R.drawable.ic_security_camera),
  DESK(R.drawable.ic_desk),
  DOOR(R.drawable.ic_door),
  EVENTS(R.drawable.ic_events),
  FIRE(R.drawable.ic_fire),
  FIRST_AID(R.drawable.ic_first_aid),
  GROCERIES(R.drawable.ic_groceries),
  KITCHEN(R.drawable.ic_kitchen),
  PARKING(R.drawable.ic_parking),
  PASSENGER(R.drawable.ic_passenger),
  POOL(R.drawable.ic_pool),
  PROMO(R.drawable.ic_promo),
  SEAT(R.drawable.ic_seat),
  SHIELD(R.drawable.ic_shield),
  TICKET(R.drawable.ic_ticket),
  TV(R.drawable.ic_tv),
  WIFI(R.drawable.ic_wifi)
}