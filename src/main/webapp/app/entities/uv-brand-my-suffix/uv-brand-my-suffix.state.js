(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('uv-brand-my-suffix', {
            parent: 'entity',
            url: '/uv-brand-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVBrand.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-brand-my-suffix/uv-brandsmySuffix.html',
                    controller: 'UVBrandMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVBrand');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('uv-brand-my-suffix-detail', {
            parent: 'uv-brand-my-suffix',
            url: '/uv-brand-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVBrand.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-brand-my-suffix/uv-brand-my-suffix-detail.html',
                    controller: 'UVBrandMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVBrand');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UVBrand', function($stateParams, UVBrand) {
                    return UVBrand.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'uv-brand-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('uv-brand-my-suffix-detail.edit', {
            parent: 'uv-brand-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-brand-my-suffix/uv-brand-my-suffix-dialog.html',
                    controller: 'UVBrandMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVBrand', function(UVBrand) {
                            return UVBrand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-brand-my-suffix.new', {
            parent: 'uv-brand-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-brand-my-suffix/uv-brand-my-suffix-dialog.html',
                    controller: 'UVBrandMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                brandName: null,
                                brandCode: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('uv-brand-my-suffix', null, { reload: 'uv-brand-my-suffix' });
                }, function() {
                    $state.go('uv-brand-my-suffix');
                });
            }]
        })
        .state('uv-brand-my-suffix.edit', {
            parent: 'uv-brand-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-brand-my-suffix/uv-brand-my-suffix-dialog.html',
                    controller: 'UVBrandMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVBrand', function(UVBrand) {
                            return UVBrand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-brand-my-suffix', null, { reload: 'uv-brand-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-brand-my-suffix.delete', {
            parent: 'uv-brand-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-brand-my-suffix/uv-brand-my-suffix-delete-dialog.html',
                    controller: 'UVBrandMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UVBrand', function(UVBrand) {
                            return UVBrand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-brand-my-suffix', null, { reload: 'uv-brand-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
