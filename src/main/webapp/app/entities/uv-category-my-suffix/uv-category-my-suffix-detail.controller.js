(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVCategoryMySuffixDetailController', UVCategoryMySuffixDetailController);

    UVCategoryMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UVCategory'];

    function UVCategoryMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UVCategory) {
        var vm = this;

        vm.uVCategory = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterJwtApp:uVCategoryUpdate', function(event, result) {
            vm.uVCategory = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
