(function () {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
