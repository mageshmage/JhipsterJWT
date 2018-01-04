(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVBrandMySuffixDialogController', UVBrandMySuffixDialogController);

    UVBrandMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UVBrand'];

    function UVBrandMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UVBrand) {
        var vm = this;

        vm.uVBrand = entity;
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
            if (vm.uVBrand.id !== null) {
                UVBrand.update(vm.uVBrand, onSaveSuccess, onSaveError);
            } else {
                UVBrand.save(vm.uVBrand, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:uVBrandUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
