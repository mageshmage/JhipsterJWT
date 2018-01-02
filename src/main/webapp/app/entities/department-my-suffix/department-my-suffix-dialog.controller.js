(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('DepartmentMySuffixDialogController', DepartmentMySuffixDialogController);

    DepartmentMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Department', 'Location', 'Employee'];

    function DepartmentMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Department, Location, Employee) {
        var vm = this;

        vm.department = entity;
        vm.clear = clear;
        vm.save = save;
        vm.locations = Location.query({filter: 'department-is-null'});
        $q.all([vm.department.$promise, vm.locations.$promise]).then(function() {
            if (!vm.department.locationId) {
                return $q.reject();
            }
            return Location.get({id : vm.department.locationId}).$promise;
        }).then(function(location) {
            vm.locations.push(location);
        });
        vm.employees = Employee.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.department.id !== null) {
                Department.update(vm.department, onSaveSuccess, onSaveError);
            } else {
                Department.save(vm.department, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:departmentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
