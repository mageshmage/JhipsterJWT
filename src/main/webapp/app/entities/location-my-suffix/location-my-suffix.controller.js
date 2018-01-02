(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('LocationMySuffixController', LocationMySuffixController);

    LocationMySuffixController.$inject = ['Location'];

    function LocationMySuffixController(Location) {

        var vm = this;

        vm.locations = [];

        loadAll();

        function loadAll() {
            Location.query(function(result) {
                vm.locations = result;
                vm.searchQuery = null;
            });
        }
    }
})();
