(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('uv-sell-unused-voucher-my-suffix', {
            parent: 'entity',
            url: '/uv-sell-unused-voucher-my-suffix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVSellUnusedVoucher.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-sell-unused-voucher-my-suffix/uv-sell-unused-vouchersmySuffix.html',
                    controller: 'UVSellUnusedVoucherMySuffixController',
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
                    $translatePartialLoader.addPart('uVSellUnusedVoucher');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('uv-sell-unused-voucher-my-suffix-detail', {
            parent: 'uv-sell-unused-voucher-my-suffix',
            url: '/uv-sell-unused-voucher-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.uVSellUnusedVoucher.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/uv-sell-unused-voucher-my-suffix/uv-sell-unused-voucher-my-suffix-detail.html',
                    controller: 'UVSellUnusedVoucherMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('uVSellUnusedVoucher');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UVSellUnusedVoucher', function($stateParams, UVSellUnusedVoucher) {
                    return UVSellUnusedVoucher.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'uv-sell-unused-voucher-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('uv-sell-unused-voucher-my-suffix-detail.edit', {
            parent: 'uv-sell-unused-voucher-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-sell-unused-voucher-my-suffix/uv-sell-unused-voucher-my-suffix-dialog.html',
                    controller: 'UVSellUnusedVoucherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVSellUnusedVoucher', function(UVSellUnusedVoucher) {
                            return UVSellUnusedVoucher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-sell-unused-voucher-my-suffix.new', {
            parent: 'uv-sell-unused-voucher-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-sell-unused-voucher-my-suffix/uv-sell-unused-voucher-my-suffix-dialog.html',
                    controller: 'UVSellUnusedVoucherMySuffixDialogController',
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
                    $state.go('uv-sell-unused-voucher-my-suffix', null, { reload: 'uv-sell-unused-voucher-my-suffix' });
                }, function() {
                    $state.go('uv-sell-unused-voucher-my-suffix');
                });
            }]
        })
        .state('uv-sell-unused-voucher-my-suffix.edit', {
            parent: 'uv-sell-unused-voucher-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-sell-unused-voucher-my-suffix/uv-sell-unused-voucher-my-suffix-dialog.html',
                    controller: 'UVSellUnusedVoucherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UVSellUnusedVoucher', function(UVSellUnusedVoucher) {
                            return UVSellUnusedVoucher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-sell-unused-voucher-my-suffix', null, { reload: 'uv-sell-unused-voucher-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('uv-sell-unused-voucher-my-suffix.delete', {
            parent: 'uv-sell-unused-voucher-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/uv-sell-unused-voucher-my-suffix/uv-sell-unused-voucher-my-suffix-delete-dialog.html',
                    controller: 'UVSellUnusedVoucherMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UVSellUnusedVoucher', function(UVSellUnusedVoucher) {
                            return UVSellUnusedVoucher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('uv-sell-unused-voucher-my-suffix', null, { reload: 'uv-sell-unused-voucher-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
