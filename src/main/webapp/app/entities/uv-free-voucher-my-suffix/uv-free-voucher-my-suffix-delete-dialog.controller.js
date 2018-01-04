(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVFreeVoucherMySuffixDeleteController',UVFreeVoucherMySuffixDeleteController);

    UVFreeVoucherMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UVFreeVoucher'];

    function UVFreeVoucherMySuffixDeleteController($uibModalInstance, entity, UVFreeVoucher) {
        var vm = this;

        vm.uVFreeVoucher = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UVFreeVoucher.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
