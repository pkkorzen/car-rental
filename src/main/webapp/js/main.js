$("document").ready(function(){
    var today = new Date();
    var dd = today.getDate();
    var dd1 = today.getDate()+1;
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd = '0'+dd
    }

    if(dd1<10) {
        dd1 = '0'+dd1
    }

    if(mm<10) {
        mm = '0'+mm
    }

    today = yyyy + '-' + mm + '-' + dd;
    var tomorrow = yyyy + '-' + mm + '-' + dd1;
    $('#start_date').attr('value', today).attr('min', today);
    $('#end_date').attr('value', tomorrow).attr('min', tomorrow);
});
