(function() {
    'use strict';
    angular
        .module('jhipsterJwtApp')
        .factory('UVFreeVoucher', UVFreeVoucher);

    UVFreeVoucher.$inject = ['$resource', 'DateUtils'];

    function UVFreeVoucher ($resource, DateUtils) {
        var resourceUrl =  'api/uv-free-vouchers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdOn = DateUtils.convertLocalDateFromServer(data.createdOn);
                        data.lastUpdatedOn = DateUtils.convertLocalDateFromServer(data.lastUpdatedOn);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.createdOn = DateUtils.convertLocalDateToServer(copy.createdOn);
                    copy.lastUpdatedOn = DateUtils.convertLocalDateToServer(copy.lastUpdatedOn);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.createdOn = DateUtils.convertLocalDateToServer(copy.createdOn);
                    copy.lastUpdatedOn = DateUtils.convertLocalDateToServer(copy.lastUpdatedOn);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
