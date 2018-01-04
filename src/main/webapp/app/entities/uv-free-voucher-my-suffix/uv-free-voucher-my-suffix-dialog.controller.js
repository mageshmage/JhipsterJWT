(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVFreeVoucherMySuffixDialogController', UVFreeVoucherMySuffixDialogController);

    UVFreeVoucherMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'UVFreeVoucher', 'UVBrand', 'UVCategory'];

    function UVFreeVoucherMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, UVFreeVoucher, UVBrand, UVCategory) {
        var vm = this;

        vm.uVFreeVoucher = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.brands = UVBrand.query({filter: 'uvfreevoucher-is-null'});
        $q.all([vm.uVFreeVoucher.$promise, vm.brands.$promise]).then(function() {
            if (!vm.uVFreeVoucher.brandId) {
                return $q.reject();
            }
            return UVBrand.get({id : vm.uVFreeVoucher.brandId}).$promise;
        }).then(function(brand) {
            vm.brands.push(brand);
        });
        vm.brands = UVCategory.query({filter: 'uvfreevoucher-is-null'});
        $q.all([vm.uVFreeVoucher.$promise, vm.brands.$promise]).then(function() {
            if (!vm.uVFreeVoucher.brandId) {
                return $q.reject();
            }
            return UVCategory.get({id : vm.uVFreeVoucher.brandId}).$promise;
        }).then(function(brand) {
            vm.brands.push(brand);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.uVFreeVoucher.id !== null) {
                UVFreeVoucher.update(vm.uVFreeVoucher, onSaveSuccess, onSaveError);
            } else {
                UVFreeVoucher.save(vm.uVFreeVoucher, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:uVFreeVoucherUpdate', result);
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
