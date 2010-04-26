
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
					console.debug('hey')
					if( selected.length == 0 ){
						$(this).toggleClass('tog')
						selected.unshift(this)
					} else {
						first = selected[0];
						last = selected[selected.length -1];
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
				}
			)
	}
)
