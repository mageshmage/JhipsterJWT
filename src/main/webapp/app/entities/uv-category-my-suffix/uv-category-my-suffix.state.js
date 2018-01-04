(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('uv-category-my-suffix', {
            parent: 'entity',
            url: '/uv-category-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVCategory.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-category-my-suffix/uv-categoriesmySuffix.html',
                    controller: 'UVCategoryMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVCategory');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('uv-category-my-suffix-detail', {
            parent: 'uv-category-my-suffix',
            url: '/uv-category-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVCategory.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-category-my-suffix/uv-category-my-suffix-detail.html',
                    controller: 'UVCategoryMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVCategory');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UVCategory', function($stateParams, UVCategory) {
                    return UVCategory.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'uv-category-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('uv-category-my-suffix-detail.edit', {
            parent: 'uv-category-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-category-my-suffix/uv-category-my-suffix-dialog.html',
                    controller: 'UVCategoryMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVCategory', function(UVCategory) {
                            return UVCategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-category-my-suffix.new', {
            parent: 'uv-category-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-category-my-suffix/uv-category-my-suffix-dialog.html',
                    controller: 'UVCategoryMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                categoryName: null,
                                categoryCode: null,
                                isEnabled: null,
                                createdOn: null,
                                lastUpdatedOn: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('uv-category-my-suffix', null, { reload: 'uv-category-my-suffix' });
                }, function() {
                    $state.go('uv-category-my-suffix');
                });
            }]
        })
        .state('uv-category-my-suffix.edit', {
            parent: 'uv-category-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-category-my-suffix/uv-category-my-suffix-dialog.html',
                    controller: 'UVCategoryMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVCategory', function(UVCategory) {
                            return UVCategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-category-my-suffix', null, { reload: 'uv-category-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-category-my-suffix.delete', {
            parent: 'uv-category-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-category-my-suffix/uv-category-my-suffix-delete-dialog.html',
                    controller: 'UVCategoryMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UVCategory', function(UVCategory) {
                            return UVCategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-category-my-suffix', null, { reload: 'uv-category-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
