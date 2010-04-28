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

function addNewNote(){
    console.debug('here')
    console.debug(slug)
    startIndex = $(selected[0]).attr('id').split('_')[2]
    endIndex = $(selected[selected.length-1]).attr('id').split('_')[2]
    
    $.post('/snippets/' + slug + '/newnote/', 
           {'startIndex' : startIndex, 'endIndex' : endIndex },
            function(data){
                alert(data); // John
            }, "json");
    return false;
}

$(document).ready(
	function(){
		var ps = 0;
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
								return;
							}else if( this == last ){
								$(this).removeClass('tog')
								selected.pop()
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
                        addnote_height = $('#addnote').height()
                        addnote_width = $('#addnote').width()
                        $('#addnote').css({'left':leftpos-4+'px',
                                           'top':toppos-addnote_height-5 + 'px'}).fadeIn()
                    }else{
                        $('#addnote').hide();
                    }
				}
			)
        highlightNotes();
	}
)
