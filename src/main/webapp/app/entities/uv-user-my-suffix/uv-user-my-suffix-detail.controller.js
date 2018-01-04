(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVUserMySuffixDetailController', UVUserMySuffixDetailController);

    UVUserMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UVUser'];

    function UVUserMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UVUser) {
        var vm = this;

        vm.uVUser = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterJwtApp:uVUserUpdate', function(event, result) {
            vm.uVUser = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
