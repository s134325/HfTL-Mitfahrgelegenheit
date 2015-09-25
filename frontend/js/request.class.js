var request = {
    trip: {
        get: {
            byGeoCoordinates: function (trip) {

                $.ajax({
                    method: "GET",
                    url: system.config.service + system.config.endpoint.trip,
                    data: {
                        "latitude": trip.from.geoCoordinate.latitude,
                        "longitude": trip.from.geoCoordinate.longitude
                    },
                    dataType: "json",
                    contentType: system.config.contentType.form
                }).done(function (data) {
                    $(system.view.results).find("table tbody").empty();
                    $.each(data.trips, function (key, value) {
                        var isPart = system.isParticipant(value.participants);
                        var isOwner = system.isOwner(value.participants);

                        var resultString =
                                "<tr>" +
                                "<td>" + value.from.city + "</td>" +
                                "<td>" + value.to.city + "</td>" +
                                "<td>" + value.price + "</td>" +
                                "<td>" + (value.freeSeats - (value.participants.length - 1)) + "</td>" +
                                "<td>" +
                                (isOwner ? "&nbsp;" : (isPart
                                        ? "<button type='button' class='btn btn-lg btn-sm unbooking' data-id='" + value.uuid + "'>Abmelden</button>"
                                        : "<button type='button' class='btn btn-lg btn-sm booking' " + (localStorage.getItem("uuid") !== null ? "" : "disabled = 'disabled'") + " data-id='" + value.uuid + "'>Buchen</button>"
                                        )) +
                                "</td>" +
                                "</tr>";


                        $(system.view.results).find("table tbody").append(resultString);

                    });
                });
            }
        },
        book: function (tripUUID) {
            var _METHOD = "POST";

            $.ajax({
                method: "POST",
                url: system.config.service + system.config.endpoint.trip + "/" + localStorage.getItem("uuid") + "/" + tripUUID,
                dataType: "json",
                contentType: system.config.contentType.json
            }).done(function (data) {
                try {
                    if (data.status.code === "BOOK")
                    {
                        system.handleFindTrip();
                    } else {
                        swal({title: "Schade", text: "Es istein Fehler aufgetreten, bitte versuche es erneut!", type: "error"});
                    }
                } catch (e) {
                    swal({title: "Schade", text: "Es istein Fehler aufgetreten, bitte versuche es erneut!", type: "error"});
                }
            });
        },
        unbook: function (tripUUID) {
            $.ajax({
                method: "DELETE",
                url: system.config.service + system.config.endpoint.trip + "/" + localStorage.getItem("uuid") + "/" + tripUUID,
                dataType: "json",
                contentType: system.config.contentType.json
            }).done(function (data) {
                try {
                    if (data.status.code === "UNBOOK")
                    {
                        system.handleFindTrip();
                    } else {
                        swal({title: "Schade", text: "Es ist ein Fehler aufgetreten, bitte versuche es erneut!", type: "error"});
                    }
                } catch (e) {
                    swal({title: "Schade", text: "Es ist ein Fehler aufgetreten, bitte versuche es erneut!", type: "error"});
                }
            });
        },
        create: function (trip) {
            $.ajax({
                method: "POST",
                url: system.config.service + system.config.endpoint.trip,
                data: JSON.stringify(trip),
                dataType: "json",
                contentType: system.config.contentType.json
            }).done(function () {
                $('#provide-trip-info-modal').modal('hide');
            });
        },
        update: function (trip) {
            var _METHOD = "PUT";

        },
        delete: function (tripID) {
            var _METHOD = "DELETE";

        }
    },
    user: {
        login: function (username, password) {
            $.ajax({
                method: "POST",
                url: system.config.service + system.config.endpoint.user.login,
                data: JSON.stringify({
                    "username": username,
                    "password": password
                }),
                dataType: "json",
                contentType: system.config.contentType.json
            }).done(function (returnData) {
                if (returnData.status.code === "SUCCESS") {
                    system.handleSuccessfulLogin(returnData);
                } else {
                    swal({title: "Fehler", text: "Der Login ist leider fehlgeschlagen, bitte versuche es erneut", type: "error"});
                }
            });
        },
        register: function (data) {
            $.ajax({
                method: "POST",
                url: system.config.service + system.config.endpoint.user.create,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: system.config.contentType.json
            }).done(function (re) {
                $('#registerModal').modal('hide');
                swal({title: "Wunderbar", text: "Deine Registrierung war erfolgreich, du kannst MiZe nun vollumfänglich nutzen.", type: "success"});
            });
        }
    }
}
;