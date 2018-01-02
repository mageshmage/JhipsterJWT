'use strict';

describe('Controller Tests', function() {

    describe('JobHistory Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockJobHistory, MockJob, MockDepartment, MockEmployee;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockJobHistory = jasmine.createSpy('MockJobHistory');
            MockJob = jasmine.createSpy('MockJob');
            MockDepartment = jasmine.createSpy('MockDepartment');
            MockEmployee = jasmine.createSpy('MockEmployee');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'JobHistory': MockJobHistory,
                'Job': MockJob,
                'Department': MockDepartment,
                'Employee': MockEmployee
            };
            createController = function() {
                $injector.get('$controller')("JobHistoryMySuffixDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipsterJwtApp:jobHistoryUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
