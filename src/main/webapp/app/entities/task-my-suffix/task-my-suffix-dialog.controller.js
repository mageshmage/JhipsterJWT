(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('TaskMySuffixDialogController', TaskMySuffixDialogController);

    TaskMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Task', 'Job'];

    function TaskMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Task, Job) {
        var vm = this;

        vm.task = entity;
        vm.clear = clear;
        vm.save = save;
        vm.jobs = Job.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.task.id !== null) {
                Task.update(vm.task, onSaveSuccess, onSaveError);
            } else {
                Task.save(vm.task, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterJwtApp:taskUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
