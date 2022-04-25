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

    var $select = $('#pais');
    $('<option>').val("---").text("--- Selecci").appendTo($select); 
    $.getJSON("/json/countries.json", function(result){
        $.each(result, function(i, field){
            $.each(field, function(i, field){
                console.log(field.name_es);
                $('<option>').val(field.name_es).text(field.name_es).appendTo($select); 
             });
         });
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
