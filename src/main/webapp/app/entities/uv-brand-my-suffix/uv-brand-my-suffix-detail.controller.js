(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVBrandMySuffixDetailController', UVBrandMySuffixDetailController);

    UVBrandMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UVBrand'];

    function UVBrandMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UVBrand) {
        var vm = this;

        vm.uVBrand = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterJwtApp:uVBrandUpdate', function(event, result) {
            vm.uVBrand = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
