package com.iasiris.muniapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.iasiris.feature.cart.Cart
import com.iasiris.feature.home.productcatalog.ProductCatalog
import com.iasiris.feature.home.productdetail.ProductDetail
import com.iasiris.feature.login.login.Login
import com.iasiris.feature.profile.Profile

@Composable
fun NavigationWrapper() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Profile
    ) { //TODO agregar if para chequear si usuario esta loggeado, si esta loggeado llevar directamente a home
        composable<Login> {
            Login(
                navigateToHome = { navigationController.navigate(ProductCatalog) }
            )
        }
        composable<ProductCatalog> {
            ProductCatalog(
                navigateToProductDetail = { navigationController.navigate(ProductDetail(id = it)) }
            )
        }
        composable<ProductDetail> { navBackStackEntry ->
            val product: ProductDetail = navBackStackEntry.toRoute()
            ProductDetail(
                product.id,
                navigateToCart = { navigationController.navigate(Cart) }
            )
        }
        composable<Cart> {
            /*
            navBackStackEntry ->
            val products: Cart = navBackStackEntry.toRoute()
            Cart(
                products,
                navigateToCheckout = { navigationController.navigate(Checkout) }
            )*/
            Cart(
                navigateToCheckout = { }
            )
        }
        composable<Profile> {
            Profile()
        }
    }
}

/*navigateToCatalog = { //EJEMPLO PARA BORRAR STACK DE NAVEGACION
    navigationController.navigate(ProductCatalog) {
        popUpTo(ProductCatalog) { inclusive = true}
    }
}*/