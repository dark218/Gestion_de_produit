angular.module('app.services', [])
.service('AuthService', function() {
	return {
		user : null,
		authorization: null,
	}
})
.factory('AuthInterceptor', function($rootScope, AuthService, localStorageService) {  
	var service = this;
    service.request = function(config) {
    	if(!AuthService.user){
    		auth = localStorageService.get('AuthService');
    		if (auth){
    			AuthService.user = auth.user;
    			$rootScope.user = auth.user;
    			AuthService.authorization = auth.authorization;
    		}
    	}

        if (AuthService.authorization) {
        	if (!config.headers.Authorization)
        		config.headers.Authorization = AuthService.authorization;
        }
        return config;
    };
    return service;
})
.factory('User', function($resource, AuthService) {
	return $resource('/api/users/:id', { id: '@id' }, {update: { method:'PUT'}});
})
.factory('Commande', function($resource) {
	return $resource('/api/commandes/:id', { id: '@id' }, {update: { method:'PUT'}});
})
.factory('Client', function($resource) {
	return $resource('/api/clients/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.factory('Produit', function($resource) {
	return $resource('/api/produits/:id', { id: '@id' }, {update: {method: 'PUT'}});
})
.service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
})