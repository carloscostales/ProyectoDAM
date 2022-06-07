$(document).ready(function () {
    $("#portada").change(function () {
        file = this.files[0];
        reader = new FileReader();
        reader.onload = function (e) {
            $("#portadaThumbnail").attr("src", e.target.result);
            $("#portadaThumbnail").attr("width", 300);
        };
        reader.readAsDataURL(file);
    });

    $("#botonCambio").prop("disabled", true);
    $("#portada").on("change", function () {
        if (!$("#portada").val()) {
            $("#botonCambio").prop("disabled", true);
        } else {
            $("#botonCambio").prop("disabled", false);
        }
    });

    $("#valoraciones :div :input").rating({displayOnly: true, step: 0.5});
});

$("#cambiarPortadaForm").validate({
    rules: {
        field: {
            required: true,
            extension: "jpg|jpeg|png|gif|svg",
        },
    },
});
jQuery.extend(jQuery.validator.messages, {
    accept: "Escoge una imagen.",
});
