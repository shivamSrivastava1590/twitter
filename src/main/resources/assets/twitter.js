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
            var item = '<div class="result"><ul>';
            for (var i = 0; i < timeline.length; i++) {
                var hrefLink = "https://twitter.com/intent/status/" + timeline[i].timeLineResponseId;
                item += '<div class="listitem">'                                                                                                  +
                    "<div onclick='openInNewTab(\""+ hrefLink + "\")'>" + '<a>' + timeline[i].timelineResponse + '</a>' + '</div>'       +
                    '<div><img src="' + timeline[i].timeLineProfileImageUrl +'"></div>'+
                    '<div class="tweetDate">' + new Date(timeline[i].timelineResponseDate) + '</div>' +
                    '</div>';
            }
            item += '</ul>';
            main.html(item);
        }
    });
}


function openInNewTab(url) {
    var win = window.open(url, '_blank');
    win.focus();
}