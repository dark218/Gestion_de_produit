(function() {
	var app = angular.module('app', ['ui.router', 'navController', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'app.controllers', 'app.services', 'multipleSelect', 'LocalStorageModule'])

	// define for requirejs loaded modules
	define('app', [], function() { return app; });

	// function for dynamic load with requirejs of a javascript module for use with a view
	// in the state definition call add property `resolve: req('/views/ui.js')`
	// or `resolve: req(['/views/ui.js'])`
	// or `resolve: req('views/ui')`
	function req(deps) {
		if (typeof deps === 'string') deps = [deps];
		return {
			deps: function ($q, $rootScope) {
				var deferred = $q.defer();
				require(deps, function() {
					$rootScope.$apply(function () {
						deferred.resolve();
					});
					deferred.resolve();
				});
				return deferred.promise;
			}
		}
	}
	app.config(function($stateProvider, $urlRouterProvider, $controllerProvider, $httpProvider, localStorageServiceProvider){
		localStorageServiceProvider.setPrefix('TradersAppV2');
		$httpProvider.interceptors.push('AuthInterceptor');
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		
		var origController = app.controller
		app.controller = function (name, constructor){
			$controllerProvider.register(name, constructor);
			return origController.apply(this, arguments);
		}

		var viewsPrefix = 'views/';

		$urlRouterProvider
			.otherwise("/")

		$stateProvider
			// you can set this to no template if you just want to use the html in the page
			.state('home', {
				url: "/",
				templateUrl: viewsPrefix + "home.html",
				data: {
					pageTitle: 'Home'
				}
			})
			.state('login', {
				url: "/login",
				templateUrl: viewsPrefix + "login.html",
				controller: 'nav'
			})
			.state('register', {
				url: "/register",
				templateUrl: viewsPrefix + "register.html",
				controller: 'RegisterController'
			})
			.state('users',{
		        url:'/users',
		        templateUrl: viewsPrefix + 'user/users.html',
		        controller:'UserListController'
		    })
		    .state('viewUser',{
			       url:'/users/:id/view',
			       templateUrl: viewsPrefix + 'user/user-view.html',
			       controller:'UserViewController'
		    })
		    .state('newUser',{
			        url:'/users/new',
			        templateUrl: viewsPrefix + 'user/user-add.html',
			        controller:'UserCreateController'
		    })
		    .state('editUser',{
			        url:'/users/:id/edit',
			        templateUrl: viewsPrefix + 'user/user-edit.html',
			        controller:'UserEditController'
		    })
			.state('commandes',{
		        url:'/commandes',
		        templateUrl: viewsPrefix + 'commande/commandes.html',
		        controller:'CommandeListController'
		    })
		    .state('viewCommande',{
			       url:'/commandes/:id/view',
			       templateUrl: viewsPrefix + 'commande/commande-view.html',
			       controller:'CommandeViewController'
		    })
		    .state('newCommande',{
			        url:'/commandes/new',
			        templateUrl: viewsPrefix + 'commande/commande-add.html',
			        controller:'CommandeCreateController'
		    })
		    .state('editCommande',{
			        url:'/commandes/:id/edit',
			        templateUrl: viewsPrefix + 'commande/commande-edit.html',
			        controller:'CommandeEditController'
		    })	    
			.state('clients',{
		        url:'/clients',
		        templateUrl: viewsPrefix + 'client/clients.html',
		        controller:'ClientListController'
		    })
		    .state('viewClient',{
			       url:'/clients/:id/view',
			       templateUrl: viewsPrefix + 'client/client-view.html',
			       controller:'ClientViewController'
		    })
		    .state('newClient',{
			        url:'/clients/new',
			        templateUrl: viewsPrefix + 'client/client-add.html',
			        controller:'ClientCreateController'
		    })
		    .state('editClient',{
			        url:'/clients/:id/edit',
			        templateUrl: viewsPrefix + 'client/client-edit.html',
			        controller:'ClientEditController'
		    })
			.state('produits',{
		        url:'/produits',
		        templateUrl: viewsPrefix + 'produit/produits.html',
		        controller:'ProduitListController'
		    })
		    .state('viewProduit',{
			       url:'/produits/:id/view',
			       templateUrl: viewsPrefix + 'produit/produit-view.html',
			       controller:'ProduitViewController'
		    })
		    .state('newProduit',{
			        url:'/produits/new',
			        templateUrl: viewsPrefix + 'produit/produit-add.html',
			        controller:'ProduitCreateController'
		    })
		    .state('editProduit',{
			        url:'/produits/:id/edit',
			        templateUrl: viewsPrefix + 'produit/produit-edit.html',
			        controller:'ProduitEditController'
		    })
	})
	.directive('updateTitle', ['$rootScope', '$timeout',
		function($rootScope, $timeout) {
			return {
				link: function(scope, element) {
					var listener = function(event, toState) {
						var title = 'Project Name';
						if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle + ' - ' + title;
						$timeout(function() {
							element.text(title);
						}, 0, false);
					};

					$rootScope.$on('$stateChangeSuccess', listener);
				}
			};
		}
	]);
}());
