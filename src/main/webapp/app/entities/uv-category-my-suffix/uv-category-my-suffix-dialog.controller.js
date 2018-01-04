(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVCategoryMySuffixDialogController', UVCategoryMySuffixDialogController);

    UVCategoryMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UVCategory'];

    function UVCategoryMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UVCategory) {
        var vm = this;

        vm.uVCategory = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.uVCategory.id !== null) {
                UVCategory.update(vm.uVCategory, onSaveSuccess, onSaveError);
            } else {
                UVCategory.save(vm.uVCategory, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:uVCategoryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdOn = false;
        vm.datePickerOpenStatus.lastUpdatedOn = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
