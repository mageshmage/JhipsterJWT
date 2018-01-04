(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVCategoryMySuffixDeleteController',UVCategoryMySuffixDeleteController);

    UVCategoryMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UVCategory'];

    function UVCategoryMySuffixDeleteController($uibModalInstance, entity, UVCategory) {
        var vm = this;

        vm.uVCategory = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UVCategory.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
