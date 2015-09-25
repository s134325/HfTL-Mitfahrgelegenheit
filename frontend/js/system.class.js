var system = {
    countrymapping: {
        "Deutschland": "GERMANY"
    },
    config: {
        service: "http://mize.msm-projects.net:1337/mize",
        endpoint: {
            trip: "/trips",
            user: {
                create: "/user",
                login: "/user/login",
                get: "/user"
            }
        },
        contentType: {
            xml: "application/xml",
            json: "application/json",
            form: "application/x-www-form-urlencoded"
        }
    },
    form: {
        login: {
            id: "#login",
            username: "#login-username",
            password: "#login-password"
        },
        logout: {
            id: "#logout"
        },
        register: {
            id: "#register",
            username: "#register-username",
            password1: "#register-password1",
            password1Label: "#register-password1-label",
            password2: "#register-password2",
            lastName: "#register-last-name",
            firstName: "#register-first-name",
            mail: "#register-mail",
            phone: "#register-phone",
            gender: "#register-gender",
            error: "#register-error"
        },
        findTrip: {
            id: "#find-trip",
            from: "#find-trip-from",
            to: "#find-trip-to",
            date: "#find-trip-date"
        },
        provideTrip: {
            id: "#provide-trip",
            submit: "#provide-trip-submit",
            from: "#provide-trip-from",
            to: "#provide-trip-to",
            date: "#provide-trip-date",
            freeSeats: "#provide-trip-freeseats",
            price: "#provide-trip-price",
            description: "#provide-trip-description"
        }
    },
    view: {
        results: "#results"
    },
    init: function () {
        try {
            var datePickerOptions = {
                startDate: "now",
                todayBtn: "linked",
                language: "de",
                calendarWeeks: true,
                autoclose: true,
                disableTouchKeyboard: true,
                todayHighlight: true
            };

            $(system.form.findTrip.date).datepicker(datePickerOptions);
            $(system.form.provideTrip.date).datepicker(datePickerOptions);
            $(system.form.provideTrip.dateReturn).datepicker(datePickerOptions);

            localStorage.removeItem("uuid");

        } catch (e) {
            return false;
        }
        return true;
    },
    registerHandler: function () {
        var _THIS = this;

        $(system.form.findTrip.id).submit(function (event) {


            event.preventDefault();
            system.handleFindTrip();

        });


        $(system.form.provideTrip.id).submit(function (event) {

            if (event.target.checkValidity()) {
                $('#provide-trip-info-modal').modal('show')
            }
            event.preventDefault();

        });
        $(system.form.provideTrip.submit).on("click", function () {

            var trip = _THIS.googleMapping($(system.form.provideTrip.from).attr("data-place"), $(system.form.provideTrip.to).attr("data-place"));

            $(system.view.results).slideUp();

            var date = $(system.form.provideTrip.date).val();
            date = date.split(".");

            trip.freeSeats = $(system.form.provideTrip.freeSeats).val();
            trip.price = $(system.form.provideTrip.price).val();
            trip.description = $(system.form.provideTrip.description).val();
            var d = new Date(date[2], date[1] - 1, date[0], 0, 0, 0, 0);
            console.log(d);
            //TODO: FIX
            trip.startTime = d.toISOString();
            trip.startTime = trip.startTime.substring(0, trip.startTime.length - 5) + "+02:00";
            trip.active = true;

            request.trip.create(trip);

        });

        $("#provide-trip-return-checkbox").on("click", function () {
            $("#provide-trip-return").slideToggle();

            if ($(system.form.provideTrip.dateReturn).attr('required'))
                $(system.form.provideTrip.dateReturn).removeAttr('required');
            else
                $(system.form.provideTrip.dateReturn).attr('required', "required");
        });


        $(system.form.login.id).submit(function (event) {

            var username = $(system.form.login.username).val();
            var password = $(system.form.login.password).val();

            event.preventDefault();

            request.user.login(username, password);

        });

        $(system.form.register.id).submit(function (event) {

            event.preventDefault();

            if ($(system.form.register.password1).val() !== $(system.form.register.password2).val())
            {
                system.displayRegisterPasswordError();
                return false;
            }

            var data = $(system.form.register.id).serializeObject();

            request.user.register(data);

        });

        $(system.form.register.username).on("keyup change", function (e) {
            $(system.form.register.mail).val($(this).val() + "@hft-leipzig.de");
        });

        $("body").delegate(".booking", "click", function () {
            var uuid = $(this).attr("data-id");

            request.trip.book(uuid);
        });

        $("body").delegate(".unbooking", "click", function () {
            var uuid = $(this).attr("data-id");

            request.trip.unbook(uuid);
        });

    },
    googleMapping: function (googleObjectFrom, googleObjectTo) {

        var trip = new Trip();

        try {
            trip.from = this.googleMappingFrom(googleObjectFrom);
            trip.to = this.googleMappingTo(googleObjectTo);
        } catch (e) {
            console.error(e);
        }

        return trip;
    },
    googleMappingFrom: function (googleObjectFrom) {
        var _TARGET = trip.from;

        try {
            googleObjectFrom = jQuery.parseJSON(googleObjectFrom);
        } catch (e) {
            console.error("Could not parse googleObjectFrom: ", googleObjectFrom, e);
        }

        try {
            _TARGET.geoCoordinate.latitude = googleObjectFrom.geometry.location.A;
            _TARGET.geoCoordinate.longitude = googleObjectFrom.geometry.location.F;
        } catch (e) {
            console.error("Could not retrieve latitude or longitude: ", googleObjectFrom, e);
        }

        try {
            $.each(googleObjectFrom.address_components, function (key, value) {
                switch (value.types[0]) {
                    case "street_number":
                        _TARGET.streetNumber = value.long_name;
                        break;
                    case "route":
                        _TARGET.street = value.long_name;
                        break;
                    case "locality":
                        _TARGET.city = value.long_name;
                        break;
                    case "postal_code":
                        _TARGET.zipCode = value.long_name;
                        break;
                    case "country":
                        _TARGET.country = system.countrymapping[value.long_name];
                        break;
                }
            });
        } catch (e) {
            console.error("Could not retrieve address: ", googleObjectFrom, e);
        }

        return _TARGET;

    },
    googleMappingTo: function (googleObjectTo) {
        var _TARGET = trip.to;

        try {
            googleObjectTo = jQuery.parseJSON(googleObjectTo);
        } catch (e) {
            console.error("Could not parse googleObjectTo: ", googleObjectTo, e);
        }

        try {
            _TARGET.geoCoordinate.latitude = googleObjectTo.geometry.location.A;
            _TARGET.geoCoordinate.longitude = googleObjectTo.geometry.location.F;
        } catch (e) {
            console.error("Could not retrieve latitude or longitude: ", googleObjectTo, e);
        }

        try {
            $.each(googleObjectTo.address_components, function (key, value) {
                switch (value.types[0]) {
                    case "street_number":
                        _TARGET.streetNumber = value.long_name;
                        break;
                    case "route":
                        _TARGET.street = value.long_name;
                        break;
                    case "locality":
                        _TARGET.city = value.long_name;
                        break;
                    case "postal_code":
                        _TARGET.zipCode = value.long_name;
                        break;
                    case "country":
                        _TARGET.country = system.countrymapping[value.long_name];
                        break;
                }
            });
        } catch (e) {
            console.error("Could not retrieve address: ", googleObjectTo, e);
        }

        return _TARGET;
    },
    displayRegisterPasswordError: function () {
        swal({title: "Uuups!", text: "Bitte stelle sicher, dass beide Passwörter gleich sind!", type: "error"});
    },
    handleSuccessfulLogin: function (returnData) {
        swal({title: "Ein Traum", text: "Der Login war erfolgreich, viel Spaß!", timer: 1000, type: "success"});
        localStorage.setItem("uuid", returnData.uuid);

        $.ajaxSetup({
            headers: {'x-uuid': localStorage.getItem("uuid")}
        });

        $(system.form.login.id).fadeOut();
        $(system.form.logout.id).fadeIn();

        $(".booking").removeAttr("disabled");

        $("#provide-trip-blocker").remove();
        $("#provide-trip-parent").removeClass("disabled");
    },
    isParticipant: function (part) {
        var isPart = false;

        $.each(part, function (key, value) {
            if (value.uuid === localStorage.getItem("uuid")) {
                isPart = true;
            }

        });
        return isPart;
    },
    isOwner: function (part) {
        var isOwner = false;

        $.each(part, function (key, value) {
            if (value.uuid === localStorage.getItem("uuid") && value.role === "OWNER") {
                isOwner = true;
            }

        });
        return isOwner;
    },
    handleSuccessfulBooking: function () {

    },
    handleSuccessfulUnBooking: function () {

    },
    handleFindTrip: function () {

        var trip = system.googleMapping($(system.form.findTrip.from).attr("data-place"), $(system.form.findTrip.to).attr("data-place"));

        request.trip.get.byGeoCoordinates(trip);

        $(system.view.results).slideDown();
    }
};

$.fn.serializeObject = function ()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};