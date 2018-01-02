(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('location-my-suffix', {
            parent: 'entity',
            url: '/location-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.location.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/location-my-suffix/locationsmySuffix.html',
                    controller: 'LocationMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('location');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('location-my-suffix-detail', {
            parent: 'location-my-suffix',
            url: '/location-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.location.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/location-my-suffix/location-my-suffix-detail.html',
                    controller: 'LocationMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('location');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Location', function($stateParams, Location) {
                    return Location.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'location-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('location-my-suffix-detail.edit', {
            parent: 'location-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/location-my-suffix/location-my-suffix-dialog.html',
                    controller: 'LocationMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Location', function(Location) {
                            return Location.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('location-my-suffix.new', {
            parent: 'location-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/location-my-suffix/location-my-suffix-dialog.html',
                    controller: 'LocationMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                streetAddress: null,
                                postalCode: null,
                                city: null,
                                stateProvince: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('location-my-suffix', null, { reload: 'location-my-suffix' });
                }, function() {
                    $state.go('location-my-suffix');
                });
            }]
        })
        .state('location-my-suffix.edit', {
            parent: 'location-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/location-my-suffix/location-my-suffix-dialog.html',
                    controller: 'LocationMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Location', function(Location) {
                            return Location.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('location-my-suffix', null, { reload: 'location-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('location-my-suffix.delete', {
            parent: 'location-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/location-my-suffix/location-my-suffix-delete-dialog.html',
                    controller: 'LocationMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Location', function(Location) {
                            return Location.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('location-my-suffix', null, { reload: 'location-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
