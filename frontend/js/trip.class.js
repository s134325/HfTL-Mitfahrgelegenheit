var Trip = function () {
    this.from = {
        address: {
            street: null,
            streetNumber: null,
            zipCode: null,
            city: null,
            country: null
        },
        geoCoordinate: {
            latitude: null,
            longitude: null
        }
    };

    this.to = {
        address: {
            street: null,
            streetNumber: null,
            zipCode: null,
            city: null,
            country: null
        },
        geoCoordinate: {
            latitude: null,
            longitude: null
        }
    };

    this.startTime = null;
    this.description = null;
    this.price = null;
    this.freeSeats = null;
    this.active = null;
};