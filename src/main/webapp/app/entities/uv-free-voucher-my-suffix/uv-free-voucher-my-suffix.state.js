(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('uv-free-voucher-my-suffix', {
            parent: 'entity',
            url: '/uv-free-voucher-my-suffix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVFreeVoucher.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-free-voucher-my-suffix/uv-free-vouchersmySuffix.html',
                    controller: 'UVFreeVoucherMySuffixController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVFreeVoucher');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('uv-free-voucher-my-suffix-detail', {
            parent: 'uv-free-voucher-my-suffix',
            url: '/uv-free-voucher-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVFreeVoucher.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-free-voucher-my-suffix/uv-free-voucher-my-suffix-detail.html',
                    controller: 'UVFreeVoucherMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVFreeVoucher');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UVFreeVoucher', function($stateParams, UVFreeVoucher) {
                    return UVFreeVoucher.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'uv-free-voucher-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('uv-free-voucher-my-suffix-detail.edit', {
            parent: 'uv-free-voucher-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-free-voucher-my-suffix/uv-free-voucher-my-suffix-dialog.html',
                    controller: 'UVFreeVoucherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVFreeVoucher', function(UVFreeVoucher) {
                            return UVFreeVoucher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-free-voucher-my-suffix.new', {
            parent: 'uv-free-voucher-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-free-voucher-my-suffix/uv-free-voucher-my-suffix-dialog.html',
                    controller: 'UVFreeVoucherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                voucherCode: null,
                                isValid: null,
                                isExpired: null,
                                createdBy: null,
                                createdOn: null,
                                lastUpdatedOn: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('uv-free-voucher-my-suffix', null, { reload: 'uv-free-voucher-my-suffix' });
                }, function() {
                    $state.go('uv-free-voucher-my-suffix');
                });
            }]
        })
        .state('uv-free-voucher-my-suffix.edit', {
            parent: 'uv-free-voucher-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-free-voucher-my-suffix/uv-free-voucher-my-suffix-dialog.html',
                    controller: 'UVFreeVoucherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVFreeVoucher', function(UVFreeVoucher) {
                            return UVFreeVoucher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-free-voucher-my-suffix', null, { reload: 'uv-free-voucher-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-free-voucher-my-suffix.delete', {
            parent: 'uv-free-voucher-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-free-voucher-my-suffix/uv-free-voucher-my-suffix-delete-dialog.html',
                    controller: 'UVFreeVoucherMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UVFreeVoucher', function(UVFreeVoucher) {
                            return UVFreeVoucher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-free-voucher-my-suffix', null, { reload: 'uv-free-voucher-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
