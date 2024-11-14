/*
push: Standard navigation to a route.

pushSingle: Navigate to a route, ensuring no duplicate entries on top of the backstack (launchSingleTop).

pop: Pops the backstack (returns to the previous screen).

pushReplaced: Replaces the entire backstack with the new route by popping up to the root (popUpTo(0)).

pushPopUpTo: Navigates to a route, clearing the backstack up to a specific route, but without including it.

pushReplaceUpTo: Similar to pushPopUpTo, but clears the backstack inclusively up to a specific route.

pushPopToStart: Navigates to a route and pops everything up to the start of the graph.

 */

package com.visionforgestudio.taskflow.jcpackages.jcnavigation

import androidx.navigation.NavController

class JcNav {
	companion object {
		fun push(navController: NavController, route: String) {
			navController.navigate(route)
		}
		
		// Navigate to a route, ensuring no duplicate entries on top of the backstack (launchSingleTop).
		fun pushSingle(navController: NavController, route: String) {
			navController.navigate(route) {
				launchSingleTop = true
			}
		}
		
		// Pops the backstack (returns to the previous screen).
		fun pop(navController: NavController) {
			navController.popBackStack()
		}
		
		// Replaces the entire backstack with the new route by popping up to the root (popUpTo(0)).
		fun pushReplaced(navController: NavController, route: String) {
			navController.navigate(route) {
				popUpTo(0) {
					inclusive = true
				}
			}
		}
		
		// Navigates to a route, clearing the backstack up to a specific route, but without including it.
		fun pushPopUpTo(navController: NavController, route: String, popUpTo: String) {
			navController.navigate(route) {
				popUpTo(popUpTo) {
					inclusive = false
				}
			}
		}
		
		// Similar to pushPopUpTo, but clears the backstack inclusively up to a specific route.
		fun pushReplaceUpTo(navController: NavController, route: String, popUpTo: String) {
			navController.navigate(route) {
				popUpTo(popUpTo) {
					inclusive = true
				}
			}
		}
		
		// Navigates to a route and pops everything up to the start of the graph.
		fun pushPopToStart(navController: NavController, route: String) {
			navController.navigate(route) {
				popUpTo(navController.graph.startDestinationId) {
					inclusive = false
				}
			}
		}
		
		// Similar to pushPopToStart, but clears the backstack inclusively up to a specific route.
		fun pushReplacePopToStart(navController: NavController, route: String) {
			navController.navigate(route) {
				popUpTo(navController.graph.startDestinationId) {
					inclusive = true
				}
			}
		}
		
		
	}
}