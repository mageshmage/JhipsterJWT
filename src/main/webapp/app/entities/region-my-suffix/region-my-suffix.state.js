(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('region-my-suffix', {
            parent: 'entity',
            url: '/region-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.region.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/region-my-suffix/regionsmySuffix.html',
                    controller: 'RegionMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('region');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('region-my-suffix-detail', {
            parent: 'region-my-suffix',
            url: '/region-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.region.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/region-my-suffix/region-my-suffix-detail.html',
                    controller: 'RegionMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('region');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Region', function($stateParams, Region) {
                    return Region.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'region-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('region-my-suffix-detail.edit', {
            parent: 'region-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/region-my-suffix/region-my-suffix-dialog.html',
                    controller: 'RegionMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Region', function(Region) {
                            return Region.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('region-my-suffix.new', {
            parent: 'region-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/region-my-suffix/region-my-suffix-dialog.html',
                    controller: 'RegionMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                regionName: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('region-my-suffix', null, { reload: 'region-my-suffix' });
                }, function() {
                    $state.go('region-my-suffix');
                });
            }]
        })
        .state('region-my-suffix.edit', {
            parent: 'region-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/region-my-suffix/region-my-suffix-dialog.html',
                    controller: 'RegionMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Region', function(Region) {
                            return Region.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('region-my-suffix', null, { reload: 'region-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('region-my-suffix.delete', {
            parent: 'region-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/region-my-suffix/region-my-suffix-delete-dialog.html',
                    controller: 'RegionMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Region', function(Region) {
                            return Region.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('region-my-suffix', null, { reload: 'region-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
