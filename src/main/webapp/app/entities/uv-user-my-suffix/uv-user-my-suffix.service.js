(function() {
    'use strict';
    angular
        .module('jhipsterJwtApp')
        .factory('UVUser', UVUser);

    UVUser.$inject = ['$resource'];

    function UVUser ($resource) {
        var resourceUrl =  'api/uv-users/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
