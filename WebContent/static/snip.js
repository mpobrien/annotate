var selected = [];

function seqWord(w1, w2){<!--{{{-->
	// -1, means w1 goes first
	// 1, means w2 goes first
	// 0, non seq
	id1s = $(w1).attr('id').split('_')
	id2s = $(w2).attr('id').split('_')
	if( id1s.length != id2s.length ) return 0;
	if( id1s[1] != id2s[1] ) return 0;
	if( id1s[2] - id2s[2] == 1 ) return 1;
	else if( id2s[2] - id1s[2] == 1 ) return -1;
	else return 0;
}<!--}}}-->

function highlightNotes(){//{{{
    if( notes ){
        for( i in notes ){
            startidx = notes[i][0]
            endidx = notes[i][1]
            var j;
            for(j = startidx; j<= endidx; j++){
                $('#w_' + 0 + '_' + j).addClass('noted')
            }

        }
    }
}//}}}

function addNewNote(){//{{{
    startIndex = $(selected[0]).attr('id').split('_')[2]
    endIndex = $(selected[selected.length-1]).attr('id').split('_')[2]
    $.post('/snippets/' + slug + '/newnote/', 
           {'startIndex' : startIndex, 'endIndex' : endIndex },
            function(data){
                alert(data); // John
            }, "json");
    return false;
}//}}}


var verbsout 

function closenotebox(){
    $('#addnote').fadeOut();
	$('#lookup, #newnote').show()
    $('#results').hide()
    $('.tog').removeClass('tog')
    selected = []
    return false;
}

var last_clicked_word;

function lookup(){//{{{
	$('#lookup, #newnote').hide()
	$('#lookupspinner').show()
    word = $(selected[0]).text();
    last_clicked_word = selected[0];
    $.get('/verbs/', 
           {'term' : word },
            function(data){
				setTimeout(
					function(){
						$('#lookupspinner').hide()
						if( data.length == 0 ){
							$('#results').html('no results&hellip;<br/> <a href="#defmodal" name="modal">Add a definition</a>').show();
						}else{
							//$('#lookup, #newnote').show()
							result_str = ''
							for( d in data ){
								result_str += "<div>" + data[d].infinitive + "</div>"
							}
							$('#results').html(result_str).show();
						}
						console.debug(data); // John
					}
					,200);
            }, "json");
    return false;
}//}}}

function saveDefinition(){
    word = $('#definition_input').val();
    translation = $('#definition_trans_input').val();
    $.aj
}

$(document).ready(
	function(){
		var ps = 0;
        $('#def_submit').click(
            function(){ saveDefinition() }
        )
		$('.annotatable').each(
			function(){
				words = $(this).text().split(/\s+/);
				$(this).html('')
				var i;
				var newhtml = '';
				for(i=0;i<words.length;i++){
					wordElement = document.createElement('span')
					this.appendChild(wordElement);
					$(wordElement).addClass('word').attr('id','w_' + ps + '_' + i).text(words[i])
					this.appendChild(document.createTextNode(" "))
				}
				ps += 1;
			}
		);

		$('.word').hover(
			function(){ if( !$(this).hasClass('tog') ) $(this).addClass('hov') },
			function(){ $(this).removeClass('hov') }
			).click(
				function(){
					if( selected.length == 0 ){
						$(this).toggleClass('tog')
						selected.unshift(this)
					} else {
						first = selected[0];
						last = selected[selected.length - 1];
						if( $(this).hasClass('tog') ){
							if( this == first ){
								$(this).removeClass('tog')
								selected.shift()
                                $('#addnote').fadeOut();
								return;
							}else if( this == last ){
								$(this).removeClass('tog')
								selected.pop()
                                $('#addnote').fadeOut();
								return;
							}else{
								$('.tog').removeClass('tog')
								$(this).addClass('tog')
								selected = []
								selected.push(this)
							}
						}
						if( seqWord(this, first) == -1 ){
							$(this).toggleClass('tog')
							selected.unshift(this)
						}else if( seqWord(this, last) == 1 ){
							$(this).toggleClass('tog')
							selected.push(this)
						}else{
							$('.tog').removeClass('tog')
							$(this).addClass('tog')
							selected = [this]
						}
					}

                    if( selected.length > 0 ){
                        leftpos = $(selected[0]).offset().left
                        toppos = $(selected[0]).offset().top 
                        selected_height = $(selected[0]).height() 
                        addnote_height = $('#addnote').height()
                        addnote_width = $('#addnote').width()
                        $('#addnote').css({'left':leftpos-4+'px',
                                           'top':toppos+selected_height + 3 + 'px'}).fadeIn()
                    }
				}
			)
        highlightNotes();
	}
)
