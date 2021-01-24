(function() {
	'use strict';

	angular.module('app').controller('ProductsController', ProductsController);

	ProductsController.$inject = [ '$http', '$scope' ];
	function ProductsController($http, $scope) {
		$scope.options = [ {
			field : "manufacture"
		}, {
			field : "model"
		}, {
			field : "typecode"
		} ];

		$scope.options1 = [ {

			field : "id"
		}, {

			field : "product"
		}, {
			field : "model"
		}, {
			field : "manufacture"
		}, {
			field : "typecode"
		}, {
			field : "locationcode"
		} ];

		$scope.options2 = [ {

			field : "product"
		}, {
			field : "model"
		}, {
			field : "manufacture"
		}, {
			field : "type_code"
		}, {
			field : "location_code"
		}, {
			field : "msrp"
		}, {
			field : "unit_cost"
		}, {
			field : "discount_rate"
		}, {
			field : "stock_qty"
		} ];

		var m = this;
		this.show = false;
		this.show1 = true;
		m.products = [];
		var self=this;
		m.getAll = getAll;
		m.deletestatus = 99;
		m.updatestatus = 99;
		m.authStatus = 99;
		m.getbyParam = getbyParam;
		m.deleteProduct = deleteProduct;
		m.updateProduct = updateProduct;
		m.sendEmail = sendEmail;
		m.auth = auth;
		init();
		function init() {

		}

		function auth(username, password) {
			var url = "/auth/" + username + "/" + password;
			var productsPromise = $http.get(url);
			productsPromise.then(function(response) {
				m.authStatus = response.data;
			});
			


		}
		function getAll() {
			var url = "/getAll";
			var productsPromise = $http.get(url);
			productsPromise.then(function(response) {
				m.products = response.data;
			});
		}

		function getbyParam(field, value) {

			var url = "/search/" + field + "/" + value;
			var productsPromise = $http.get(url);
			productsPromise.then(function(response) {
				m.products = response.data;
			});
		}

		function deleteProduct(id) {
			var url = "/delete/" + id;
			var productsPromise = $http.get(url);
			productsPromise.then(function(response) {
				m.deletestatus = response.data;
			});
		}

		function updateProduct(field, value, id) {
			var url = "/update/" + field + "/" + value + "/" + id;
			var productsPromise = $http.get(url);
			productsPromise.then(function(response) {
				m.updatestatus = response.data;
			});
		}

		function sendEmail(name, emailid, query, message) {
			var url = "/email/" + name + "/" + emailid + "/" + query + "/"
					+ message;
			var productsPromise = $http.get(url);
			productsPromise.then(function(response) {
				m.emailStatus = response.data;
			});

		}
	}
})();