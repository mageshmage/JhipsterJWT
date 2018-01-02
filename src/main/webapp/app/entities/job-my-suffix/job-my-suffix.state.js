(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('job-my-suffix', {
            parent: 'entity',
            url: '/job-my-suffix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.job.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/job-my-suffix/jobsmySuffix.html',
                    controller: 'JobMySuffixController',
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
                    $translatePartialLoader.addPart('job');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('job-my-suffix-detail', {
            parent: 'job-my-suffix',
            url: '/job-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.job.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/job-my-suffix/job-my-suffix-detail.html',
                    controller: 'JobMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('job');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Job', function($stateParams, Job) {
                    return Job.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'job-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('job-my-suffix-detail.edit', {
            parent: 'job-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/job-my-suffix/job-my-suffix-dialog.html',
                    controller: 'JobMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Job', function(Job) {
                            return Job.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('job-my-suffix.new', {
            parent: 'job-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/job-my-suffix/job-my-suffix-dialog.html',
                    controller: 'JobMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                jobTitle: null,
                                minSalary: null,
                                maxSalary: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('job-my-suffix', null, { reload: 'job-my-suffix' });
                }, function() {
                    $state.go('job-my-suffix');
                });
            }]
        })
        .state('job-my-suffix.edit', {
            parent: 'job-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/job-my-suffix/job-my-suffix-dialog.html',
                    controller: 'JobMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Job', function(Job) {
                            return Job.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('job-my-suffix', null, { reload: 'job-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('job-my-suffix.delete', {
            parent: 'job-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/job-my-suffix/job-my-suffix-delete-dialog.html',
                    controller: 'JobMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Job', function(Job) {
                            return Job.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('job-my-suffix', null, { reload: 'job-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
