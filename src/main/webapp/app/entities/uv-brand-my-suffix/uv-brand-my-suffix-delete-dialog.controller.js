(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVBrandMySuffixDeleteController',UVBrandMySuffixDeleteController);

    UVBrandMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UVBrand'];

    function UVBrandMySuffixDeleteController($uibModalInstance, entity, UVBrand) {
        var vm = this;

        vm.uVBrand = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UVBrand.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
