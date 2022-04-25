$(document).ready(function(){
    $('#portadaThumbnail').hide();
    
    $('#portada').change(function() {
        file = this.files[0];
        reader = new FileReader();
        reader.onload = function(e) {
            $('#portadaThumbnail').show();
            $('#portadaThumbnail').attr('src', e.target.result);
            $('#portadaThumbnail').attr('height', 390);
        };
        reader.readAsDataURL(file);
    });

});

$("#nuevoLibroForm").validate({
      rules: {
        field: {
              required: true,
              extension: "jpg|jpeg|png|gif|svg"
        }
      }
});
jQuery.extend(jQuery.validator.messages, {
  accept: 'Escoge una imagen.'
});
