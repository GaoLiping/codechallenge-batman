/**
 * Created by liping on 28/04/15.
 */
angular.module('myApp', ['ngResource'])
    .factory('MessageResource', function ($resource) {
        return $resource({}, {}, {
            get: {method:'GET', url:'test/messages/recent'},
            save:{method:'POST', url:'test/messages/names/:userName', params:{userName:'@userName'}}
        });
    })
    .controller('codeChallenge', ['$scope', 'MessageResource', function (scope, service) {
        scope.getRecent = function () {
            service.get({}, function (data) {
                scope.recent = data;
            });
        };

        scope.getRecent();

        scope.postMessage = function(userName) {
            service.save({}, {userName:userName}, function(){
                scope.getRecent();
            });
        };
    }]);