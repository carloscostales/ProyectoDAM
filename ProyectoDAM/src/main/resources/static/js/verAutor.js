$(document).ready(function () {
    $("#foto").change(function () {
        file = this.files[0];
        reader = new FileReader();
        reader.onload = function (e) {
            $("#fotoThumbnail").attr("src", e.target.result);
            $("#fotoThumbnail").attr("width", 300);
        };
        reader.readAsDataURL(file);
    });

    $("#botonCambio").prop("disabled", true);
    $("#foto").on("change", function () {
        if (!$("#foto").val()) {
            $("#botonCambio").prop("disabled", true);
        } else {
            $("#botonCambio").prop("disabled", false);
        }
    });
});

$("#cambiarFotoForm").validate({
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
