function updateStatus(){
    var main = $("#output");
    $.ajax({
        url: 'http://localhost:8080/api/1.0/twitter/timeline',
        type: "GET",
        dataType: 'JSON',
        success: function(response){
            console.log(response);
            var timeline = response.timelineResponse;
            console.log(timeline);
            var item = '<table class="table table-striped"> <tbody>';
            for (var i = 0; i < timeline.length; i++) {
                var hrefLink = "https://twitter.com/intent/status/" + timeline[i].timeLineResponseId;
                item += '<div><tr>'                                                                                                                             +
                            "<td onclick='openInNewTab(\""+ hrefLink + "\")'>" + '<span style="color: beige">' + timeline[i].timelineResponse + '</span></td>'       +
                            '<td>' + new Date(timeline[i].timelineResponseDate) + '</td>'                                                                  +
                        '</tr></div>';
            }
            item += '</tbody></table>';
            main.html(item);
        }
    });
}


function openInNewTab(url) {
    var win = window.open(url, '_blank');
    win.focus();
}