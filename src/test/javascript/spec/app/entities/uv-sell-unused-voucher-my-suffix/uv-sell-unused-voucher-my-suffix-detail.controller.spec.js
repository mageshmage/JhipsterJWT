'use strict';

describe('Controller Tests', function() {

    describe('UVSellUnusedVoucher Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockUVSellUnusedVoucher, MockUVBrand, MockUVCategory;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockUVSellUnusedVoucher = jasmine.createSpy('MockUVSellUnusedVoucher');
            MockUVBrand = jasmine.createSpy('MockUVBrand');
            MockUVCategory = jasmine.createSpy('MockUVCategory');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'UVSellUnusedVoucher': MockUVSellUnusedVoucher,
                'UVBrand': MockUVBrand,
                'UVCategory': MockUVCategory
            };
            createController = function() {
                $injector.get('$controller')("UVSellUnusedVoucherMySuffixDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipsterJwtApp:uVSellUnusedVoucherUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
