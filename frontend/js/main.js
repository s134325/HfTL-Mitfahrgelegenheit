$(function () {
    if (map.init()) {
        map.findTripFrom();
        map.findTripTo();

        map.provideTripFrom();
        map.provideTripTo();
    }

    bootstrap.init();

//TODO: remove
    $.ajaxSetup({
        headers: {'x-uuid': "62df9167-9a91-4373-8086-fc9091b131d7"}
    });


    if (system.init()) {
        system.registerHandler();
    }
});