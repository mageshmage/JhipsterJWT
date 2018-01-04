(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('uv-user-my-suffix', {
            parent: 'entity',
            url: '/uv-user-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVUser.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-user-my-suffix/uv-usersmySuffix.html',
                    controller: 'UVUserMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVUser');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('uv-user-my-suffix-detail', {
            parent: 'uv-user-my-suffix',
            url: '/uv-user-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVUser.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-user-my-suffix/uv-user-my-suffix-detail.html',
                    controller: 'UVUserMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVUser');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UVUser', function($stateParams, UVUser) {
                    return UVUser.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'uv-user-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('uv-user-my-suffix-detail.edit', {
            parent: 'uv-user-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-user-my-suffix/uv-user-my-suffix-dialog.html',
                    controller: 'UVUserMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVUser', function(UVUser) {
                            return UVUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-user-my-suffix.new', {
            parent: 'uv-user-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-user-my-suffix/uv-user-my-suffix-dialog.html',
                    controller: 'UVUserMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userName: null,
                                password: null,
                                email: null,
                                isVerifiedUser: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('uv-user-my-suffix', null, { reload: 'uv-user-my-suffix' });
                }, function() {
                    $state.go('uv-user-my-suffix');
                });
            }]
        })
        .state('uv-user-my-suffix.edit', {
            parent: 'uv-user-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-user-my-suffix/uv-user-my-suffix-dialog.html',
                    controller: 'UVUserMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVUser', function(UVUser) {
                            return UVUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-user-my-suffix', null, { reload: 'uv-user-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-user-my-suffix.delete', {
            parent: 'uv-user-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-user-my-suffix/uv-user-my-suffix-delete-dialog.html',
                    controller: 'UVUserMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UVUser', function(UVUser) {
                            return UVUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-user-my-suffix', null, { reload: 'uv-user-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
