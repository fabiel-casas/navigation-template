package com.zagart.navigation.template.navigation.deeplinks

import android.net.Uri
import com.zagart.navigation.template.presentation.navigation.BonusNavBarDestination
import com.zagart.navigation.template.presentation.navigation.BonusBoxDestination
import com.zagart.navigation.template.presentation.navigation.CookingNavBarDestination
import com.zagart.navigation.template.presentation.navigation.Destination
import com.zagart.navigation.template.presentation.navigation.HomeNavBarDestination
import com.zagart.navigation.template.presentation.navigation.MyListNavBarDestination
import com.zagart.navigation.template.presentation.navigation.ProductDetailsDestination
import com.zagart.navigation.template.presentation.navigation.ProductsNavBarDestination
import com.zagart.navigation.template.ui.Tab

object DeeplinkConverter {

//    fun apply(uri: Uri?): List<Destination> {
//        val segments = uri?.pathSegments.orEmpty()
//
//        if (segments.isEmpty()) {
//            return emptyList()
//        }
//
//        val backstackIndex = segments.first().asDestination().args.backstackIndex
//
//        return segments.fold(mutableListOf()) { destinations, segment ->
//            destinations.apply { addAll(segment.asDestinations(backstackIndex)) }
//        }
//    }
//}
//
//private fun String.asDestinations(backstackIndex: Int = -1): List<Destination> {
//    val destinations = mutableListOf<Destination>()
//
//    when (val destination = asDestination(backstackIndex)) {
//        is BonusBoxDestination -> destinations.apply {
//            add(BonusBackstack())
//            add(destination)
//        }
//
//        else -> destinations.add(destination)
//    }
//
//    return destinations
//}
//
//private fun String.asDestination(backstackIndex: Int = -1): Destination {
//    return when (this) {
//        "home" -> HomeNavBarDestination
//        "bonus" -> BonusNavBarDestination
//        "bonusbox" -> BonusBoxDestination(Destination.Args(Tab.BONUS.ordinal))
//        "cooking" -> CookingNavBarDestination
//        "products" -> ProductsNavBarDestination
//        "mylist" -> MyListNavBarDestination
//        "product" -> ProductDetailsDestination(id = "1", Destination.Args(backstackIndex))
//        else -> throw IllegalArgumentException("Unsupported destination: $this")
//    }
}