$(document).ready(function(){
    var $select = $('#pais');
    $('<option>').val("---").text("--- Selecciona un pais ---").appendTo($select); 
    $.getJSON("/json/countries.json", function(result){
        $.each(result, function(i, field){
            $.each(field, function(i, field){
                if($paisSelected == field.name_es)
                    $('<option selected>').val(field.name_es).text(field.name_es).appendTo($select);
                else
                    $('<option>').val(field.name_es).text(field.name_es).appendTo($select);
             });
         });
     });
});
