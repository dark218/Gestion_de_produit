angular.module('navController', [])
	.controller('nav', function($rootScope, $scope, $state, $http, AuthService, localStorageService) {
		$scope.title = 'Gestion des ordinateurs';
		$scope.isUrl = function(url) {
			if (url === '#') return false;
			return ('#' + $state.$current.url.source + '/').indexOf(url + '/') === 0;
		};

		$scope.login = function() {
			var base64Credential = btoa($scope.user.username + ':' + $scope.user.password);
			AuthService.authorization = 'Basic ' + base64Credential;
			$http.get('login', {}).success(function(res) {
				if (res.authenticated) {
					$scope.message = null;
					AuthService.user = res;
					$rootScope.user = AuthService.user;
					localStorageService.set('AuthService',{'user':AuthService.user,'authorization':AuthService.authorization})
					$state.go('home');
				} else {
					$scope.message = 'Erreur de username ou mot de passe';
				}
			}).error(function(error) {
				$scope.message = 'Erreur de username ou mot de passe';
			});
		};	
		
		$scope.logout = function(){
			$rootScope.user = null;
			AuthService.user = null;
			AuthService.authorization = null;	
			localStorageService.remove('AuthService');
			$state.go('login');
		};
		
		$scope.pages = [
		    {
			   name: 'Utilsateurs',
			   url: '#/users'
		    },		       
			{
				name: 'Commandes',
				url: '#/commandes'
			},
			{
				name: 'Clients',
				url: '#/clients'
			},
			{
				name: 'Produits',
				url: '#/produits'
			}
		]
	});