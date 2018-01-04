(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVSellUnusedVoucherMySuffixDialogController', UVSellUnusedVoucherMySuffixDialogController);

    UVSellUnusedVoucherMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'UVSellUnusedVoucher', 'UVBrand', 'UVCategory'];

    function UVSellUnusedVoucherMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, UVSellUnusedVoucher, UVBrand, UVCategory) {
        var vm = this;

        vm.uVSellUnusedVoucher = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.brands = UVBrand.query({filter: 'uvsellunusedvoucher-is-null'});
        $q.all([vm.uVSellUnusedVoucher.$promise, vm.brands.$promise]).then(function() {
            if (!vm.uVSellUnusedVoucher.brandId) {
                return $q.reject();
            }
            return UVBrand.get({id : vm.uVSellUnusedVoucher.brandId}).$promise;
        }).then(function(brand) {
            vm.brands.push(brand);
        });
        vm.brands = UVCategory.query({filter: 'uvsellunusedvoucher-is-null'});
        $q.all([vm.uVSellUnusedVoucher.$promise, vm.brands.$promise]).then(function() {
            if (!vm.uVSellUnusedVoucher.brandId) {
                return $q.reject();
            }
            return UVCategory.get({id : vm.uVSellUnusedVoucher.brandId}).$promise;
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
            if (vm.uVSellUnusedVoucher.id !== null) {
                UVSellUnusedVoucher.update(vm.uVSellUnusedVoucher, onSaveSuccess, onSaveError);
            } else {
                UVSellUnusedVoucher.save(vm.uVSellUnusedVoucher, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:uVSellUnusedVoucherUpdate', result);
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
