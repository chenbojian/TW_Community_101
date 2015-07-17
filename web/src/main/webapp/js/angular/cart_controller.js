var App = angular.module('App', []);

App.controller('Controller', function($scope, $http) {

    $scope.is_fake = true;
    $scope.selected_items_id = [];
    $scope.selected_items = [];

    var give_fake_data = function() {
      if ($scope.is_fake) {
          $scope.selected_items_id = [1, 2, 3, 4];
          $scope.selected_items.push({"id": 1, "name": "Goods A", "price": 1234, "pic": "//baidu.com", "quantity": 1});
          $scope.selected_items.push({"id": 2, "name": "Goods B", "price": 1234, "pic": "//baidu.com", "quantity": 1});
          $scope.selected_items.push({"id": 3, "name": "Goods C", "price": 1234, "pic": "//baidu.com", "quantity": 1});
          $scope.selected_items.push({"id": 4, "name": "Goods D", "price": 1234, "pic": "//baidu.com", "quantity": 1});

      }
    };
    give_fake_data();

    $scope.remove_selected_item = function(index) {
        $scope.selected_items.splice(index, 1);
    };

    $scope.more_selected_item = function(index) {
        $scope.selected_items[index]["quantity"] = $scope.selected_items[index]["quantity"] + 1;
    };

    $scope.less_selected_item = function(index) {
        if ($scope.selected_items[index]["quantity"] > 0) {
            $scope.selected_items[index]["quantity"] = $scope.selected_items[index]["quantity"] - 1;
        }
    };

    $scope.bill = {};
    $scope.bill.total = 0;
    var calculateTotals = function() {
        var total = 0;
        for (var i = 0, len = $scope.selected_items.length; i < len; i++) {
            total = total + $scope.selected_items[i]["price"] * $scope.selected_items[i]["quantity"];
        }
        $scope.bill.total = total;
    };

    $scope.$watch('selected_items', calculateTotals, true);

    $scope.phone = 123;
    $scope.address = "Beijing";
});
