$("document").ready(function(){

    function leadingZero(i){
        return (i<10)? '0'+i: i;
    }

    var today = new Date();
    var tomorrow = new Date();


    today = today.getFullYear() + '-' + leadingZero(today.getMonth()+1) + '-' + leadingZero(today.getDate());
    tomorrow = tomorrow.getFullYear() + '-' + leadingZero(tomorrow.getMonth()+1) + '-' + leadingZero(tomorrow.getDate()+1);

    $('#start_date').attr('value', today).attr('min', today).on('change', function(){
        var startDate = new Date($('#start_date').val());
        var endDate = startDate.getFullYear() + '-' + leadingZero(startDate.getMonth()+1) + '-' + leadingZero(startDate.getDate()+1);
        $('#end_date').attr('min', endDate)
    });
    $('#end_date').attr('value', tomorrow).attr('min', tomorrow);
});
