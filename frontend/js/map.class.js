var map = {
    map: null,
    directionsDisplay: new google.maps.DirectionsRenderer(),
    directionsService: new google.maps.DirectionsService(),
    init: function () {
        try {
            this.map = new google.maps.Map(document.getElementById('map-canvas'), {
                zoom: 8,
                center: {lat: 52.5192, lng: 13.4061},
                disableDefaultUI: true
            });
        } catch (e) {
            return false;
        }
        return true;
    },
    findTripFrom: function () {
        var _THIS = this;
        var _MAP = _THIS.map;

        var autocomplete = new google.maps.places.Autocomplete($(system.form.findTrip.from).get(0));

        autocomplete.bindTo('bounds', _MAP);
        autocomplete.setTypes(['address']);

        var infoWindow = new google.maps.InfoWindow();

        var marker = new google.maps.Marker({
            map: _MAP,
            anchorPoint: new google.maps.Point(0, -29)
        });

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            infoWindow.close();
            marker.setVisible(false);

            var place = autocomplete.getPlace();

            $(system.form.findTrip.from).attr("data-place", JSON.stringify(place));

            if (!place.geometry) {
                window.alert("Autocomplete's returned place contains no geometry");
                return;
            }

            if (place.geometry.viewport) {
                _MAP.fitBounds(place.geometry.viewport);
            } else {
                _MAP.setCenter(place.geometry.location);
                _MAP.setZoom(17);
            }

            marker.setIcon({
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(35, 35)
            });
            marker.setPosition(place.geometry.location);
            marker.setVisible(false);

            var address = '';
            if (place.address_components) {
                address = [
                    (place.address_components[0] && place.address_components[0].short_name || ''),
                    (place.address_components[1] && place.address_components[1].short_name || ''),
                    (place.address_components[2] && place.address_components[2].short_name || '')
                ].join(' ');
            }

            infoWindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
            infoWindow.open(_MAP, marker);


            _THIS.displayDirections();
        });
    },
    findTripTo: function () {
        var _THIS = this;
        var _MAP = _THIS.map;

        var autocomplete = new google.maps.places.Autocomplete($(system.form.findTrip.to).get(0));

        autocomplete.bindTo('bounds', _MAP);
        autocomplete.setTypes(['address']);

        var infoWindow = new google.maps.InfoWindow();
        var marker = new google.maps.Marker({
            map: _MAP,
            anchorPoint: new google.maps.Point(0, -29)
        });

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            infoWindow.close();
            marker.setVisible(false);
            var place = autocomplete.getPlace();

            $(system.form.findTrip.to).attr("data-place", JSON.stringify(place));


            if (!place.geometry) {
                window.alert("Autocomplete's returned place contains no geometry");
                return;
            }

            if (place.geometry.viewport) {
                _MAP.fitBounds(place.geometry.viewport);
            } else {
                _MAP.setZoom(17);
            }


            marker.setIcon({
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(35, 35)
            });

            marker.setPosition(place.geometry.location);
            marker.setVisible(false);

            var address = '';
            if (place.address_components) {
                address = [
                    (place.address_components[0] && place.address_components[0].short_name || ''),
                    (place.address_components[1] && place.address_components[1].short_name || ''),
                    (place.address_components[2] && place.address_components[2].short_name || '')
                ].join(' ');
            }

            infoWindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
            infoWindow.open(_MAP, marker);

            _THIS.displayDirections();
        });
    },
    displayDirections: function () {

        var _THIS = this;
        var _MAP = _THIS.map;

        _THIS.directionsDisplay.setMap(_MAP);

        var from = $(system.form.findTrip.from).val();
        var to = $(system.form.findTrip.to).val();

        if (from !== null && to !== null) {
            var request = {
                origin: from,
                destination: to,
                travelMode: google.maps.TravelMode.DRIVING
            };

            _THIS.directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    _THIS.directionsDisplay.setDirections(response);
                }
            });
        }
    },
    provideTripFrom: function () {
        var _THIS = this;
        var _MAP = _THIS.map;

        var autocomplete = new google.maps.places.Autocomplete($(system.form.provideTrip.from).get(0));

        autocomplete.bindTo('bounds', _MAP);
        autocomplete.setTypes(['address']);

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            var place = autocomplete.getPlace();

            $(system.form.provideTrip.from).attr("data-place", JSON.stringify(place));
        });

    },
    provideTripTo: function () {
        var _THIS = this;
        var _MAP = _THIS.map;

        var autocomplete = new google.maps.places.Autocomplete($(system.form.provideTrip.to).get(0));

        autocomplete.bindTo('bounds', _MAP);
        autocomplete.setTypes(['address']);

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            var place = autocomplete.getPlace();

            $(system.form.provideTrip.to).attr("data-place", JSON.stringify(place));

        });
    }
};