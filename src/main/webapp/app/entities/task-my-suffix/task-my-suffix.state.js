(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('task-my-suffix', {
            parent: 'entity',
            url: '/task-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.task.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/task-my-suffix/tasksmySuffix.html',
                    controller: 'TaskMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('task');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('task-my-suffix-detail', {
            parent: 'task-my-suffix',
            url: '/task-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.task.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/task-my-suffix/task-my-suffix-detail.html',
                    controller: 'TaskMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('task');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Task', function($stateParams, Task) {
                    return Task.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'task-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('task-my-suffix-detail.edit', {
            parent: 'task-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/task-my-suffix/task-my-suffix-dialog.html',
                    controller: 'TaskMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Task', function(Task) {
                            return Task.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('task-my-suffix.new', {
            parent: 'task-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/task-my-suffix/task-my-suffix-dialog.html',
                    controller: 'TaskMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                title: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('task-my-suffix', null, { reload: 'task-my-suffix' });
                }, function() {
                    $state.go('task-my-suffix');
                });
            }]
        })
        .state('task-my-suffix.edit', {
            parent: 'task-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/task-my-suffix/task-my-suffix-dialog.html',
                    controller: 'TaskMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Task', function(Task) {
                            return Task.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('task-my-suffix', null, { reload: 'task-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('task-my-suffix.delete', {
            parent: 'task-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/task-my-suffix/task-my-suffix-delete-dialog.html',
                    controller: 'TaskMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Task', function(Task) {
                            return Task.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('task-my-suffix', null, { reload: 'task-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
