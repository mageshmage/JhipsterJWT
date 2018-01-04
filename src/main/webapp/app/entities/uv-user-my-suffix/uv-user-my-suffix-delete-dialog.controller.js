(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVUserMySuffixDeleteController',UVUserMySuffixDeleteController);

    UVUserMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UVUser'];

    function UVUserMySuffixDeleteController($uibModalInstance, entity, UVUser) {
        var vm = this;

        vm.uVUser = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UVUser.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
