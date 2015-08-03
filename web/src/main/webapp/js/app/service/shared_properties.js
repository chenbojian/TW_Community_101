angular.module('webApp')
.service('SharedProperties', function() {
        var allgoods;
        return {
            getAllGoods: function() {
                return allgoods;
            },
            setAllGoods: function(value) {
                allgoods = value;
            }
        }
    });