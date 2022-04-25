$(document).ready(function(){
    $('#fotoThumbnail').hide();
    
    $('#foto').change(function() {
        file = this.files[0];
        reader = new FileReader();
        reader.onload = function(e) {
            $('#fotoThumbnail').show();
            $('#fotoThumbnail').attr('src', e.target.result);
            $('#fotoThumbnail').attr('height', 300);
        };
        reader.readAsDataURL(file);
    });
});

$("#nuevoAutorForm").validate({
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
