(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVUserMySuffixDialogController', UVUserMySuffixDialogController);

    UVUserMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UVUser'];

    function UVUserMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UVUser) {
        var vm = this;

        vm.uVUser = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.uVUser.id !== null) {
                UVUser.update(vm.uVUser, onSaveSuccess, onSaveError);
            } else {
                UVUser.save(vm.uVUser, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:uVUserUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
