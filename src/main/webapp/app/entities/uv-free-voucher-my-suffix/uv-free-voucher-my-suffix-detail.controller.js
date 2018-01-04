(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVFreeVoucherMySuffixDetailController', UVFreeVoucherMySuffixDetailController);

    UVFreeVoucherMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UVFreeVoucher', 'UVBrand', 'UVCategory'];

    function UVFreeVoucherMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UVFreeVoucher, UVBrand, UVCategory) {
        var vm = this;

        vm.uVFreeVoucher = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterJwtApp:uVFreeVoucherUpdate', function(event, result) {
            vm.uVFreeVoucher = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
