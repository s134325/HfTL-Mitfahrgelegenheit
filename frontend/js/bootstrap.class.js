
var bootstrap = {
    init: function () {

        $('#tripCarousel').carousel({
            interval: false
        });

        $('[data-toggle="tooltip"]').tooltip();

        $('#infoModal').on('show.bs.modal', function (event) {

            var button = $(event.relatedTarget);
            var id = button.data('tripid');

            var modal = $(this);
            modal.find('.id').text(id);
        });




        $('#provide-trip-info-modal').on('show.bs.modal', function (event) {

            var from = $(system.form.provideTrip.from).val();
            var to = $(system.form.provideTrip.to).val();
            var date = $(system.form.provideTrip.date).val();
            var price = $(system.form.provideTrip.price).val();
            var description = $(system.form.provideTrip.description).val();
            var vehicle = $("#provide-trip-vehicle").val();


            $("#provide-trip-1-from").text(from);
            $("#provide-trip-1-to").text(to);
            $("#provide-trip-1-date").text(date);
            $("#provide-trip-1-vehicle").text(vehicle);


            $("#provide-trip-1-price").text(price);
            $("#provide-trip-1-description").text(description);


            if (!$("#provide-trip-return-checkbox").is(':checked')) {
                $("#provide-trip-2").hide();
            } else {
                $("#provide-trip-2").show();

                var dateReturn = $(system.form.provideTrip.dateReturn).val();

                $("#provide-trip-2-from").text(to);
                $("#provide-trip-2-to").text(from);
                $("#provide-trip-2-date").text(dateReturn);
                $("#provide-trip-2-vehicle").text(vehicle);

            }
        });
    }
};
