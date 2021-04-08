angular.module('app.controllers', [])
.controller('RegisterController',function($scope, $http){
	$scope.user = {};
	$scope.showrole = false;
	$scope.show = true;
	$scope.required = true;
	$scope.roles = ['ROLE_USER', 'ROLE_ADMIN'];
	$scope.registerUser = function(){
		if($scope.user.password != $scope.user.confirmpassword)
			$scope.error = 'les mots de passes ne correspondent pas';
		else
			$http.post('register', $scope.user).success(function(res) {
				$scope.success = 'Authentification reussie!';
				$scope.user = {};
			}).error(function(error) {
				$scope.error = error.message;
			});
	}
})
.controller('UserListController', function($scope, $state, popupService, $window, User, AuthService) {
	$scope.users = User.query();
	$scope.cancreate = false;
	if(!AuthService.user)
		$scope.message = 'Veuillez vous connectez';
	else
		$scope.cancreate =  AuthService.user.principal.role == 'ROLE_ADMIN';
		
	$scope.deleteUser = function(user) {
		if (popupService.showPopup('Voulez vous supprimez ce utilisateur?')) {
			user.$delete(function() {
		        $scope.users = User.query(); 
		        $state.go('users');
			});
	    }
	};

})
.controller('UserViewController', function($scope, $stateParams, User) {
	$scope.user = User.get({ id: $stateParams.id });
})
.controller('UserCreateController', function($scope, $state, $stateParams, User) {
	$scope.roles = ['ROLE_USER', 'ROLE_ADMIN'];
	$scope.showrole = true;
	$scope.show = true;
	$scope.required = true;
	$scope.user = new User();
	
	$scope.addUser = function() {
		$scope.user.$save(function() {
		$scope.success = 'User created';
    	$state.go('users');
    });
  };
})
.controller('UserEditController', function($scope, $state, $stateParams, User, AuthService) {
	$scope.userid = AuthService.user.principal.id;
	$scope.show = $state.current.name == 'editUser' && $state.params.id == $scope.userid;
	$scope.required = $state.params.id == $scope.userid;
	$scope.showrole = AuthService.user.principal.role == 'ROLE_ADMIN';
	$scope.roles = ['ROLE_USER', 'ROLE_ADMIN'];
	$scope.$state = $state;
	$scope.updateUser = function() {
	    $scope.user.$update(function() {
	    	$state.go('users');
	    });
	};

	$scope.loadUser = function() {
		$scope.user = User.get({ id: $stateParams.id });
	};
	
	$scope.loadUser();
})
.controller('CommandeListController', function($scope, $state, popupService, $window, Commande, AuthService) {
	$scope.commandes = Commande.query();

	if(!AuthService.user)
		$scope.message = 'Veuillez vous connectez';
	
	$scope.deleteCommande = function(commande) {
		if (popupService.showPopup('Voulez vous supprimez cette commande?')) {
			commande.$delete(function() {
		        $scope.commandes = Commande.query(); 
		        $state.go('commandes');
		        
			});
	    }
	};
})
.controller('CommandeViewController', function($scope, $stateParams, Commande) {
	$scope.commande = Commande.get({ id: $stateParams.id });
})
.controller('CommandeCreateController', function($scope, $state, $stateParams, Commande,Produit, Client) {
	$scope.clients=Client.query();
	$scope.produits=Produit.query();
	$scope.commande = new Commande();
	$scope.addCommande = function() {
    $scope.commande.$save(function() {
    	$state.go('commandes');
    });
  };
})
.controller('CommandeEditController', function($scope, $state, $stateParams, Commande, Produit, Client) {
	$scope.clients=Client.query();
	$scope.produits=Produit.query();
	$scope.updateCommande = function() {
	    $scope.commande.$update(function() {
	    	$state.go('commandes');
	    });
	};

	$scope.loadCommande = function() {
		$scope.commande = Commande.get({ id: $stateParams.id });
	};
	
	$scope.loadCommande();
})


.controller('ClientListController', function($scope, $state, popupService, $window, Client, AuthService) {
	$scope.clients = Client.query();
	
	if(!AuthService.user)
		$scope.message = 'Veuillez vous connectez';
	
	$scope.deleteClient = function(client) {
		if (popupService.showPopup('Voulez vous supprimez ce client?')) {
			client.$delete(function() {
		        $scope.clients = Client.query(); 
		        $state.go('clients');
			});
	    }
	};
})
.controller('ClientViewController', function($scope, $stateParams, Client) {
	$scope.client = Client.get({ id: $stateParams.id });
})
.controller('ClientCreateController', function($scope, $state, $stateParams, Client) {
	$scope.client = new Client();
	$scope.addClient = function() { 
    $scope.client.$save(function() {
    	$state.go('clients');
    });
  };
})
.controller('ClientEditController', function($scope, $state, $stateParams, Client) {
	$scope.updateClient = function() { 
	    $scope.client.$update(function() {
	    	$state.go('clients');
	    });
	};

	$scope.loadClient = function() {
		$scope.client = Client.get({ id: $stateParams.id });
	};
	
	$scope.loadClient();
})


.controller('ProduitListController', function($rootScope, $scope, $state, popupService, $window, Produit, AuthService) {
	$scope.produits = Produit.query();
	if(!AuthService.user)
		$scope.message = 'Veuillez vous connectez';
	$scope.deleteProduit = function(produit) {
		if (popupService.showPopup('Voulez vous supprimez ce Produit?')) {
			produit.$delete(function() {
		        $scope.produits = Produit.query(); 
		        $state.go('produits');
			});
	    }
	};
})
.controller('ProduitViewController', function($scope, $stateParams, Produit) {
	$scope.produit = Produit.get({ id: $stateParams.id });
})
.controller('ProduitCreateController', function($scope, $state, $stateParams, Produit) {

	$scope.produit = new Produit(); 
	$scope.addProduit = function() {
    $scope.produit.$save(function() {
    	$state.go('produits');
    });
  };
})
.controller('ProduitEditController', function($scope, $state, $stateParams, Produit) {
	
	$scope.updateProduit = function() {
	    $scope.produit.$update(function() {
	    	$state.go('produits');
	    });
	};

	$scope.loadProduit = function() {
		$scope.produit = Produit.get({ id: $stateParams.id });
	};
	
	$scope.loadProduit();
});
