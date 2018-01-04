(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVSellUnusedVoucherMySuffixDeleteController',UVSellUnusedVoucherMySuffixDeleteController);

    UVSellUnusedVoucherMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UVSellUnusedVoucher'];

    function UVSellUnusedVoucherMySuffixDeleteController($uibModalInstance, entity, UVSellUnusedVoucher) {
        var vm = this;

        vm.uVSellUnusedVoucher = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UVSellUnusedVoucher.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
