(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVBrandMySuffixController', UVBrandMySuffixController);

    UVBrandMySuffixController.$inject = ['UVBrand'];

    function UVBrandMySuffixController(UVBrand) {

        var vm = this;

        vm.uVBrands = [];

        loadAll();

        function loadAll() {
            UVBrand.query(function(result) {
                vm.uVBrands = result;
                vm.searchQuery = null;
            });
        }
    }
})();
