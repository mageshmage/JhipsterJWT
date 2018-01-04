(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVUserMySuffixController', UVUserMySuffixController);

    UVUserMySuffixController.$inject = ['UVUser'];

    function UVUserMySuffixController(UVUser) {

        var vm = this;

        vm.uVUsers = [];

        loadAll();

        function loadAll() {
            UVUser.query(function(result) {
                vm.uVUsers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
