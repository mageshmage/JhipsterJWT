(function() {
    'use strict';

    angular
        .module('jhipsterJwtApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('employee-my-suffix', {
            parent: 'entity',
            url: '/employee-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.employee.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/employee-my-suffix/employeesmySuffix.html',
                    controller: 'EmployeeMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('employee');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('employee-my-suffix-detail', {
            parent: 'employee-my-suffix',
            url: '/employee-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterJwtApp.employee.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/employee-my-suffix/employee-my-suffix-detail.html',
                    controller: 'EmployeeMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('employee');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Employee', function($stateParams, Employee) {
                    return Employee.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'employee-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('employee-my-suffix-detail.edit', {
            parent: 'employee-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee-my-suffix/employee-my-suffix-dialog.html',
                    controller: 'EmployeeMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Employee', function(Employee) {
                            return Employee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('employee-my-suffix.new', {
            parent: 'employee-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee-my-suffix/employee-my-suffix-dialog.html',
                    controller: 'EmployeeMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                firstName: null,
                                lastName: null,
                                email: null,
                                phoneNumber: null,
                                hireDate: null,
                                salary: null,
                                commissionPct: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('employee-my-suffix', null, { reload: 'employee-my-suffix' });
                }, function() {
                    $state.go('employee-my-suffix');
                });
            }]
        })
        .state('employee-my-suffix.edit', {
            parent: 'employee-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee-my-suffix/employee-my-suffix-dialog.html',
                    controller: 'EmployeeMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Employee', function(Employee) {
                            return Employee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('employee-my-suffix', null, { reload: 'employee-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('employee-my-suffix.delete', {
            parent: 'employee-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/employee-my-suffix/employee-my-suffix-delete-dialog.html',
                    controller: 'EmployeeMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Employee', function(Employee) {
                            return Employee.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('employee-my-suffix', null, { reload: 'employee-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
