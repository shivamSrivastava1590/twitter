function updateStatus(){
    var main = $("#output");
    $.ajax({
        url: 'http://localhost:8080/api/1.0/twitter/timeline',
        type: "GET",
        dataType: 'JSON',
        success: function(response){
            var timeline = response.timeLineResponse;
            var item = '<br>';
            for (var i = 0; i < timeline.length; i++) {
                item += '<div>' +
                            timeline[i] +
                        '</div>';
            }
            main.html(item);
        }
    });
}