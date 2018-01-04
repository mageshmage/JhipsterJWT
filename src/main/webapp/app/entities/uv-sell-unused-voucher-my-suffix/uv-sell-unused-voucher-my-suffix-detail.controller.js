(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVSellUnusedVoucherMySuffixDetailController', UVSellUnusedVoucherMySuffixDetailController);

    UVSellUnusedVoucherMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UVSellUnusedVoucher', 'UVBrand', 'UVCategory'];

    function UVSellUnusedVoucherMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UVSellUnusedVoucher, UVBrand, UVCategory) {
        var vm = this;

        vm.uVSellUnusedVoucher = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterJwtApp:uVSellUnusedVoucherUpdate', function(event, result) {
            vm.uVSellUnusedVoucher = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
