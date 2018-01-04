'use strict';

describe('Controller Tests', function() {

    describe('UVFreeVoucher Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockUVFreeVoucher, MockUVBrand, MockUVCategory;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockUVFreeVoucher = jasmine.createSpy('MockUVFreeVoucher');
            MockUVBrand = jasmine.createSpy('MockUVBrand');
            MockUVCategory = jasmine.createSpy('MockUVCategory');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'UVFreeVoucher': MockUVFreeVoucher,
                'UVBrand': MockUVBrand,
                'UVCategory': MockUVCategory
            };
            createController = function() {
                $injector.get('$controller')("UVFreeVoucherMySuffixDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipsterJwtApp:uVFreeVoucherUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
