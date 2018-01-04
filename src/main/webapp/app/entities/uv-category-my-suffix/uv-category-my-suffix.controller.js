(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .controller('UVCategoryMySuffixController', UVCategoryMySuffixController);

    UVCategoryMySuffixController.$inject = ['UVCategory'];

    function UVCategoryMySuffixController(UVCategory) {

        var vm = this;

        vm.uVCategories = [];

        loadAll();

        function loadAll() {
            UVCategory.query(function(result) {
                vm.uVCategories = result;
                vm.searchQuery = null;
            });
        }
    }
})();
